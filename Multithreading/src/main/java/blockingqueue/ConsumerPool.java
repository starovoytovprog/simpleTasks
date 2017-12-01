package blockingqueue;

import java.util.concurrent.CountDownLatch;

/**
 * Пул консьюмеров, обрабатывают ExecutableTask из очереди
 *
 * @author starovoytov
 * @created 01.12.2017
 * @$Author$
 * @$Revision$
 */
public class ConsumerPool
{
	private final MyBlockingQueue queue;
	private final int threadCount;
	private final CountDownLatch latch;

	public ConsumerPool(MyBlockingQueue queue, int threadCount)
	{
		this.queue = queue;
		this.threadCount = threadCount;
		latch = new CountDownLatch(threadCount);
	}

	public void start()
	{
		for (int i = 0; i < threadCount; i++)
		{
			new ExecuteThread().start();
		}
	}

	public CountDownLatch getLatch()
	{
		return latch;
	}

	private class ExecuteThread extends Thread
	{
		@Override
		public void run()
		{
			ExecutableTask task;

			while ((task = queue.get()) != null)
			{
				task.execute();
			}

			latch.countDown();
		}
	}
}
