package blockingqueue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование ProducerPool {@link ProducerPool}
 *
 * @author starovoytov
 * @created 01.12.2017
 * @$Author$
 * @$Revision$
 */
public class ProducerPoolTest
{
	@Test
	public void producerPoolOneThreadTest() throws InterruptedException
	{
		poolTest(1, 10);
	}

	@Test
	public void producerPoolMultiThreadTest() throws InterruptedException
	{
		poolTest(30, 100);
	}

	private void poolTest(int threadCount, int countTaskForThread) throws InterruptedException
	{
		MyBlockingQueue queue = new MyBlockingQueue();
		ProducerPool pool = new ProducerPool(queue, threadCount, countTaskForThread);
		pool.start();
		pool.getLatch().await();
		assertEquals(threadCount * countTaskForThread, queue.getSize());
	}
}
