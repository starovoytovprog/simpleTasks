package blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Пул консьюмеров, обрабатывают ExecutableTask из очереди
 *
 * @author Starovoytov
 * @since 01.12.2017
 */
public class ConsumerPool {

	private final MyBlockingQueue queue;
	private final int threadCount;
	private final CountDownLatch latch;
	private final List<ExecuteThread> threads;

	/**
	 * Конструктор пула обработчиков
	 *
	 * @param queue       Очередь, из которой потоки-обработчики будут выбирать задачи на обработку
	 * @param threadCount Количество потоков-обработчиков
	 */
	public ConsumerPool(MyBlockingQueue queue, int threadCount) {
		this.queue = queue;
		this.threadCount = threadCount;
		latch = new CountDownLatch(threadCount);
		threads = new ArrayList<>(threadCount);
	}

	/**
	 * Старт потоков-обработчиков
	 */
	public void start() {
		for (int i = 0; i < threadCount; i++) {
			ExecuteThread thread = new ExecuteThread();
			threads.add(thread);
			thread.start();
		}
	}

	/**
	 * Ожидание завершения работы потоков-обработчиков
	 *
	 * @throws InterruptedException Получен сигнал прерывания
	 */
	public void await() throws InterruptedException {
		latch.await();
	}

	/**
	 * Команда потокам-обработчикам завершить работу, после обработки всех, имеющихся заданий в очереди, не ожидая новых
	 */
	public void stop() {
		for (ExecuteThread thread : threads) {
			thread.interrupt();
		}
		threads.clear();
	}

	/**
	 * Класс потока-обработчика
	 */
	private class ExecuteThread extends Thread {
		@Override
		public void run() {
			try {
				ExecutableTask task;

				while (!isInterrupted() || queue.getSize() > 0) {
					synchronized (queue) {
						task = queue.get();
						if (task == null) {
							try {
								queue.wait();
							}
							catch (InterruptedException e) {
								return;
							}
						}
					}

					if (task != null) {
						task.execute();
					}
				}
			}
			finally {
				latch.countDown();
			}
		}
	}
}
