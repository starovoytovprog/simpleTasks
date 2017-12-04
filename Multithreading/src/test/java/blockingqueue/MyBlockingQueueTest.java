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
	ExecutableTaskCreator mockCreator;

	@Before
	public void init()
	{
		mockCreator = new MockTaskCreator();
	}

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
		consumers.getLatch().await();

		verify(mockCreator.create(), times(threadCount * countTaskForThread * 2)).execute();
		assertEquals(queue.getSize(), EMPTY);
	}

	private void addInQueue(MyBlockingQueue queue, int threadCount, int countTaskForThread, ExecutableTaskCreator mockTaskCreator) throws InterruptedException
	{
		ProducerPool pool = new ProducerPool(queue, threadCount, countTaskForThread, mockTaskCreator);
		pool.start();
		pool.getLatch().await();
	}

	@After
	public void validate()
	{
		validateMockitoUsage();
	}
}
