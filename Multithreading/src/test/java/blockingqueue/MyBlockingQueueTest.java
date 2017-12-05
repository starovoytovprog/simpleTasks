package blockingqueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Тестирование MyBlockingQueue {@link MyBlockingQueue}
 *
 * @author starovoytov
 * @created 30.11.2017
 * @$Author$
 * @$Revision$
 */
public class MyBlockingQueueTest
{
	private static final int EMPTY = 0;
	private ExecutableTaskCreator mockCreator;

	@Before
	public void init()
	{
		mockCreator = new MockTaskCreator();
	}

	/**
	 * Тестирование добавления задачи в очередь
	 */
	@Test
	public void myBlockingQueuePutTest()
	{
		MyBlockingQueue queue = new MyBlockingQueue();
		assertEquals(queue.getSize(), EMPTY);

		for (int i = 1; i <= 10; i++)
		{
			queue.put(mockCreator.create());
			assertEquals(queue.getSize(), i);
		}
	}

	/**
	 * Тестирование получение задачи из очереди
	 */
	@Test
	public void myBlockingQueueGetTest()
	{
		MyBlockingQueue queue = new MyBlockingQueue();

		int count = 10;

		for (int i = 0; i < count; i++)
		{
			queue.put(mockCreator.create());
		}

		for (int i = 0; i < count; i++)
		{
			queue.get();
		}

		assertEquals(queue.getSize(), EMPTY);
	}

	/**
	 * Тестирование очереди с изпользованием пулов добавления и обработки
	 *
	 * @throws InterruptedException
	 */
	@Test
	public void queueProducerConsumerTest() throws InterruptedException
	{
		int threadCount = 10;
		int countTaskForThread = 10_000;
		MyBlockingQueue queue = new MyBlockingQueue();
		addInQueue(queue, threadCount, countTaskForThread, mockCreator);

		int consumerThreadCount = 5;
		ConsumerPool consumers = new ConsumerPool(queue, consumerThreadCount);
		consumers.start();

		addInQueue(queue, threadCount, countTaskForThread, mockCreator);

		consumers.stop();
		consumers.await();

		verify(mockCreator.create(), times(threadCount * countTaskForThread * 2)).execute();
		assertEquals(queue.getSize(), EMPTY);
	}

	/**
	 * Добавление задач в очередь
	 *
	 * @param queue Очередь, в которую добавляются задачи
	 * @param threadCount Количество потоков пула генератора задач
	 * @param countTaskForThread Количество задач, генерируемых каждым потоком
	 * @param mockTaskCreator Фабрика задач
	 * @throws InterruptedException
	 */
	private void addInQueue(MyBlockingQueue queue, int threadCount, int countTaskForThread, ExecutableTaskCreator mockTaskCreator) throws InterruptedException
	{
		ProducerPool pool = new ProducerPool(queue, threadCount, countTaskForThread, mockTaskCreator);
		pool.start();
		pool.await();
	}

	@After
	public void validate()
	{
		validateMockitoUsage();
	}
}
