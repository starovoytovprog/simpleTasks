package blockingqueue;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
	@Test
	public void myBlockingQueuePutTest()
	{
		MyBlockingQueue queue = new MyBlockingQueue();
		assertEquals(queue.getSize(), 0);

		for (int i = 1; i <= 10; i++)
		{
			queue.put(new SimpleTask());
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
			queue.put(new SimpleTask());
		}

		for (int i = 0; i < count; i++)
		{
			queue.get();
		}

		assertEquals(queue.getSize(), 0);
	}

	@Test
	public void queueProducerConsumerTest() throws InterruptedException
	{
		SimpleTask.clearCheckSum();
		MyBlockingQueue queue = new MyBlockingQueue();
		int checkSum = addInQueue(queue, 10, 10_000);
		ConsumerPool consumers = new ConsumerPool(queue, 5);
		consumers.start();
		checkSum += addInQueue(queue, 10, 10_000);
		consumers.stop();
		consumers.getLatch().await();

		assertEquals(checkSum, SimpleTask.getCheckSum());
		assertEquals(queue.getSize(), 0);
	}

	private int addInQueue(MyBlockingQueue queue, int threadCount, int countTaskForThread) throws InterruptedException
	{
		int startValue = UniqueIdGenerator.nextId() + 1;
		int checkSum = 0;
		for (int i = startValue; i <  startValue + threadCount * countTaskForThread; i++)
		{
			checkSum += i;
		}

		ProducerPool pool = new ProducerPool(queue, threadCount, countTaskForThread);
		pool.start();
		pool.getLatch().await();
		return checkSum;
	}
}
