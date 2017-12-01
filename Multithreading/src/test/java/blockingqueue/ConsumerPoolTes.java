package blockingqueue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование ConsumerPool {@link ConsumerPool}
 *
 * @author starovoytov
 * @created 01.12.2017
 * @$Author$
 * @$Revision$
 */
public class ConsumerPoolTes
{
	@Test
	public void consumerPoolOneThreadTest() throws InterruptedException
	{
		testConsumer(1, 10000);
	}

	@Test
	public void consumerPoolMultiThreadTest() throws InterruptedException
	{
		testConsumer(10, 10);
	}

	private void testConsumer(int threadCount, int queueSize) throws InterruptedException
	{
		SimpleTask.clearCheckSum();
		MyBlockingQueue queue = new MyBlockingQueue();
		ConsumerPool pool = new ConsumerPool(queue, threadCount);

		int startValue = UniqueIdGenerator.nextId() + 1;
		int checkSum = 0;
		for (int i = startValue; i <= startValue + queueSize; i++)
		{
			queue.put(new SimpleTask());
			checkSum += i;
		}

		pool.start();
		pool.stop();
		pool.getLatch().await();
		assertEquals(0, queue.getSize());
		assertEquals(SimpleTask.getCheckSum(), checkSum);
	}
}
