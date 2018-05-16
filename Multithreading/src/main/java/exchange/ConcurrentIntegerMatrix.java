package exchange;

/**
 * Матрица объектов
 *
 * @author Starovoytov
 * @since 30.11.2017
 */
@SuppressWarnings("unchecked")
public class ConcurrentIntegerMatrix {
	private final ConcurrentObject<Integer>[][] matrix;

	/**
	 * Конструктор матрицы
	 *
	 * @param arrayCount Количество массивов
	 * @param arraySize  Расмер массивов
	 */
	public ConcurrentIntegerMatrix(int arrayCount, int arraySize) {
		matrix = new ConcurrentObject[arrayCount][arraySize];

		for (int i = 0; i < arrayCount; i++) {
			for (int j = 0; j < arraySize; j++) {
				matrix[i][j] = new ConcurrentObject<>(arraySize*i + j);
			}
		}
	}

	/**
	 * Посчитать контрольную сумму матрицы
	 *
	 * @return Контрольная сумма
	 */
	public int getCheckSum() {
		int sum = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
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
	public void exchange(int i1, int j1, int i2, int j2) {
		ConcurrentObject object1 = matrix[i1][j1];
		ConcurrentObject object2 = matrix[i2][j2];

		while (!exchange(object1, object2)) {
			Thread.currentThread().yield();
		}
	}

	/**
	 * Получить объект из матрицы по координатам
	 *
	 * @param i Номер массива
	 * @param j Номер элемента в массиве
	 * @return Объект из матрицы
	 */
	public ConcurrentObject getObject(int i, int j) {
		return matrix[i][j];
	}

	/**
	 * Производит обмен объектов
	 *
	 * @param object1 Первый объект
	 * @param object2 Второй объект
	 * @return true, если замена была успешной
	 */
	private boolean exchange(ConcurrentObject object1, ConcurrentObject object2) {
		if (object1.tryLock()) {
			if (object2.tryLock()) {
				Object buf = object1.getValue();
				object1.setValue(object2.getValue());
				object1.unLock();
				object2.setValue(buf);
				object2.unLock();

				return true;
			}
			else {
				object1.unLock();
			}
		}

		return false;
	}
}
