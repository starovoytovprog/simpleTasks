package blockingqueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Тестирование ConsumerPool {@link ConsumerPool}
 *
 * @author Starovoytov
 * @since 01.12.2017
 */
public class ConsumerPoolTes {
	private static final int EMPTY = 0;
	private ExecutableTask mockTask;

	@Before
	public void init() {
		mockTask = mock(ExecutableTask.class);
	}

	/**
	 * Тестирование обработчика задач в одном потоке
	 *
	 * @throws InterruptedException Получен сигнал прерывания
	 */
	@Test
	public void consumerPoolOneThreadTest() throws InterruptedException {
		testConsumer(1, 10000);
	}

	/**
	 * Тестирование обработчика задач в нескольких потоках
	 *
	 * @throws InterruptedException Получен сигнал прерывания
	 */
	@Test
	public void consumerPoolMultiThreadTest() throws InterruptedException {
		testConsumer(10, 10000);
	}

	/**
	 * Тестирование обработчика
	 *
	 * @param threadCount Количество потоков-обработчиков
	 * @param queueSize   Размер очереди
	 * @throws InterruptedException Получен сигнал прерывания
	 */
	private void testConsumer(int threadCount, int queueSize) throws InterruptedException {
		MyBlockingQueue queue = new MyBlockingQueue();
		ConsumerPool pool = new ConsumerPool(queue, threadCount);

		for (int i = 0; i < queueSize; i++) {
			queue.put(mockTask);
		}

		pool.start();
		pool.stop();
		pool.await();
		assertEquals(EMPTY, queue.getSize());
		verify(mockTask, times(queueSize)).execute();
	}

	@After
	public void validate() {
		validateMockitoUsage();
	}
}
