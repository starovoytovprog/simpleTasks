package exchange;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование ConcurrentIntegerMatrix {@link ConcurrentIntegerMatrix}
 *
 * @author starovoytov
 * @created 30.11.2017
 * @$Author$
 * @$Revision$
 */
public class ConcurrentIntegerMatrixTest
{
	private static final int N = 10; // N коллекций из задачи
	private static final int L = 10; // L объектов из задачи
	private static final int K = 100; // K потоков из задачи
	private static final int K_count = 10000; // Сколько перестановок должен выполнить каждый поток

	private static final int checkSum; // Рассчётная контрольная сумма для проверки целостности матрицы

	static
	{
		int sum = 0;

		for (int i = 0; i < N * L; i++)
		{
			sum += i;
		}

		checkSum = sum;
	}

	@Test
	public void concurrentMatrixTestConstructor()
	{
		ConcurrentIntegerMatrix matrix = new ConcurrentIntegerMatrix(N, L);
		assertEquals(checkSum, matrix.getCheckSum());
	}

	@Test
	public void concurrentMatrixTestExchange()
	{
		ConcurrentIntegerMatrix matrix = new ConcurrentIntegerMatrix(N, L);

		int i1 = 0;
		int j1 = 0;
		int i2 = N - 1;
		int j2 = N - 1;
		Object value1 = matrix.getObject(i1, j1).getValue();
		Object value2 = matrix.getObject(i2, j2).getValue();

		matrix.exchange(0, 0, N - 1, L - 1);

		assertEquals(checkSum, matrix.getCheckSum());
		assertEquals(value1, matrix.getObject(i2, j2).getValue());
		assertEquals(value2, matrix.getObject(i1, j1).getValue());
	}

	@Test
	public void concurrentMatrixTestMultithradingExchange() throws InterruptedException
	{
		ConcurrentIntegerMatrix matrix = new ConcurrentIntegerMatrix(N, L);

		List<ExchangeThread> threads = new ArrayList<>();

		for (int i = 0; i < K; i++)
		{
			threads.add(new ExchangeThread(matrix));
		}

		for (ExchangeThread thread : threads)
		{
			thread.start();
		}

		for (ExchangeThread thread : threads)
		{
			thread.join();
		}

		assertEquals(checkSum, matrix.getCheckSum());
	}

	private class ExchangeThread extends Thread
	{
		private final ConcurrentIntegerMatrix matrix;
		private final Random r = new Random();

		public ExchangeThread(ConcurrentIntegerMatrix matrix)
		{
			this.matrix = matrix;
		}

		@Override
		public void run()
		{
			for (int i = 0; i < K_count; i++)
			{
				exchange();
			}
		}

		private void exchange()
		{
			int i1 = r.nextInt(N);
			int j1 = r.nextInt(L);
			int i2 = r.nextInt(N);
			int j2 = r.nextInt(L);

			matrix.exchange(i1, j1, i2, j2);
		}
	}
}
