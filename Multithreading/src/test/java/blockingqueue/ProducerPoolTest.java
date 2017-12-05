package blockingqueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.validateMockitoUsage;

/**
 * Тестирование ProducerPool {@link ProducerPool}
 *
 * @author Starovoytov
 * @since 01.12.2017
 */
public class ProducerPoolTest
{
	private MockTaskCreator mockTaskCreator;

	@Before
	public void init()
	{
		mockTaskCreator = new MockTaskCreator();
	}

	/**
	 * Тестирование пула добавления задач с одним потоком
	 *
	 * @throws InterruptedException
	 */
	@Test
	public void producerPoolOneThreadTest() throws InterruptedException
	{
		poolTest(1, 10);
	}

	/**
	 * Тестирование пула добавления задач с несколькими потоками
	 *
	 * @throws InterruptedException
	 */
	@Test
	public void producerPoolMultiThreadTest() throws InterruptedException
	{
		poolTest(30, 100);
	}

	/**
	 * Тестирование пула добавления задач
	 *
	 * @param threadCount Количество потоков-генераторов
	 * @param countTaskForThread Количество задач, генерируемых каждым потоком
	 * @throws InterruptedException
	 */
	private void poolTest(int threadCount, int countTaskForThread) throws InterruptedException
	{
		MyBlockingQueue queue = new MyBlockingQueue();
		ProducerPool pool = new ProducerPool(queue, threadCount, countTaskForThread, mockTaskCreator);
		pool.start();
		pool.await();
		assertEquals(threadCount * countTaskForThread, queue.getSize());
	}

	@After
	public void validate()
	{
		validateMockitoUsage();
	}
}
