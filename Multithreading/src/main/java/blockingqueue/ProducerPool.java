package blockingqueue;

import java.util.concurrent.CountDownLatch;

/**
 * Пул продюсеров, генерирует SimpleTask в блокирующую очередь
 *
 * @author Starovoytov
 * @since 01.12.2017
 */
public class ProducerPool
{
	private final MyBlockingQueue queue;
	private final CountDownLatch latch;
	private final int threadCount;
	private final int countTaskForThread;
	private final ExecutableTaskCreator taskCreator;

	/**
	 * Конструктор пула генераторов задач
	 *
	 * @param queue Очередь, в которую добавляются задачи
	 * @param threadCount Количество потоков-генераторов
	 * @param countTaskForThread Количество задач, добавляемых каждым потоком
	 * @param taskCreator Фабрика, формирующая новые задачи
	 */
	public ProducerPool(MyBlockingQueue queue, int threadCount, int countTaskForThread, ExecutableTaskCreator taskCreator)
	{
		this.queue = queue;
		latch = new CountDownLatch(threadCount);
		this.threadCount = threadCount;
		this.countTaskForThread = countTaskForThread;
		this.taskCreator = taskCreator;
	}

	/**
	 * Старт потоков-генераторов
	 */
	public void start()
	{
		for (int i = 0; i < threadCount; i++)
		{
			new GenerateThread().start();
		}
	}

	/**
	 * Ожидание выполнения потоков-генераторов
	 *
	 * @throws InterruptedException Получен сигнал прерывания
	 */
	public void await() throws InterruptedException
	{
		latch.await();
	}

	/**
	 * Класс потока-генератора
	 */
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
