package exchange;

import org.junit.Test;

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
	public void ConcurrentMatrixTestConstructor()
	{
		ConcurrentIntegerMatrix matrix = new ConcurrentIntegerMatrix(N, L);
		assertEquals(checkSum, matrix.getCheckSum());
	}

	@Test
	public void ConcurrentMatrixTestExchange()
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

}
