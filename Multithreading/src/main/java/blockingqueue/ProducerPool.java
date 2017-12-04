package blockingqueue;

import java.util.concurrent.CountDownLatch;

/**
 * Пул продюсеров, генерирует SimpleTask в блокирующую очередь
 *
 * @author starovoytov
 * @created 01.12.2017
 * @$Author$
 * @$Revision$
 */
public class ProducerPool
{
	private final MyBlockingQueue queue;
	private final CountDownLatch latch;
	private final int threadCount;
	private final int countTaskForThread;
	private final ExecutableTaskCreator taskCreator;

	public ProducerPool(MyBlockingQueue queue, int threadCount, int countTaskForThread, ExecutableTaskCreator taskCreator)
	{
		this.queue = queue;
		latch = new CountDownLatch(threadCount);
		this.threadCount = threadCount;
		this.countTaskForThread = countTaskForThread;
		this.taskCreator = taskCreator;
	}

	public void start()
	{
		for (int i = 0; i < threadCount; i++)
		{
			new GenerateThread().start();
		}
	}

	public CountDownLatch getLatch()
	{
		return latch;
	}

	private class GenerateThread extends Thread
	{
		@Override
		public void run()
		{
			for (int i = 0; i < countTaskForThread; i++)
			{
				queue.put(taskCreator.create());
				synchronized (queue)
				{
					queue.notify();
				}
			}

			latch.countDown();
		}
	}
}
