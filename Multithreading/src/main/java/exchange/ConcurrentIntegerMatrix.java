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

	/**
	 * Производит замену объектов матрицы
	 *
	 * @param i1 номер строки первого объекта
	 * @param j1 номер столбца первого объекта
	 * @param i2 номер строки второго объекта
	 * @param j2 номер столбца второго объекта
	 */
	public void exchange(int i1, int j1, int i2, int j2)
	{
		ConcurrentObject object1 = matrix[i1][j1];
		ConcurrentObject object2 = matrix[i2][j2];

		exchange(object1, object2);
	}

	public ConcurrentObject getObject(int i, int j)
	{
		return matrix[i][j];
	}

	private void exchange(ConcurrentObject object1, ConcurrentObject object2)
	{
		Object buf = object1.getValue();
		object1.setValue(object2.getValue());
		object2.setValue(buf);
	}
}
