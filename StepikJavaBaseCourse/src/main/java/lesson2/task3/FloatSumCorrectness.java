package lesson2.task3;

/**
 * Реализуйте метод, возвращающий ответ на вопрос: правда ли, что a + b = c?
 * Допустимая погрешность – 0.0001 (1E-4)
 * Можно использовать класс Math и его методы. Класс Math доступен всегда, импортировать его не надо.
 *
 * Sample Input: 0.1 0.2 0.3; Sample Output: true
 *
 * @author Starovoytov
 * @since 03.04.2018
 */
public class FloatSumCorrectness
{
	private static final double CORRECTNESS = 0.0001;

	public static boolean doubleExpression(double a, double b, double c)
	{
		return Math.abs(a + b - c) < CORRECTNESS;
	}
}
