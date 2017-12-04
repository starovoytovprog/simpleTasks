package blockingqueue;

import java.util.ArrayList;
import java.util.List;
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
	private final List<ExecuteThread> threads;

	public ConsumerPool(MyBlockingQueue queue, int threadCount)
	{
		this.queue = queue;
		this.threadCount = threadCount;
		latch = new CountDownLatch(threadCount);
		threads = new ArrayList<>(threadCount);
	}

	public void start()
	{
		for (int i = 0; i < threadCount; i++)
		{
			ExecuteThread thread = new ExecuteThread();
			threads.add(thread);
			thread.start();
		}
	}

	public CountDownLatch getLatch()
	{
		return latch;
	}

	public void stop()
	{
		for (ExecuteThread thread : threads)
		{
			thread.interrupt();
		}
	}

	private class ExecuteThread extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				ExecutableTask task;

				while (!isInterrupted() || queue.getSize() > 0)
				{
					synchronized (queue)
					{
						task = queue.get();
						if (task == null)
						{
							try
							{
								queue.wait();
							}
							catch (InterruptedException e)
							{
								return;
							}
						}
					}

					if (task != null)
					{
						task.execute();
					}
				}
			}
			finally
			{
				latch.countDown();
			}
		}
	}
}
