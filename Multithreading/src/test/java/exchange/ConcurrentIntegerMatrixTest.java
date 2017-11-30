package exchange;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование ConcurrentMatrix {@link ConcurrentIntegerMatrix}
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
}
