package exchange;

/**
 * Матрица объектов
 *
 * @author starovoytov
 * @created 30.11.2017
 * @$Author$
 * @$Revision$
 */
public class ConcurrentIntegerMatrix
{
	private final ConcurrentObject<Integer>[][] matrix;

	public ConcurrentIntegerMatrix(int arrayCount, int arraySize)
	{
		matrix = new ConcurrentObject[arrayCount][arraySize];

		for (int i = 0; i < arrayCount; i++)
		{
			for (int j = 0; j < arraySize; j++)
			{
				matrix[i][j] = new ConcurrentObject<>(arraySize * i + j);
			}
		}
	}

	public int getCheckSum()
	{
		int sum = 0;

		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix[i].length; j++)
			{
				sum += matrix[i][j].getValue();
			}
		}

		return sum;
	}
}
