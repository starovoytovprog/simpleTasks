package lesson4.task2;

/**
 * Реализуйте метод sqrt(), вычисляющий квадратный корень числа. В отличие от Math.sqrt(), это метод при передаче отрицательного параметра должен бросать исключение java.lang.IllegalArgumentException с сообщением "Expected non-negative number, got ?", где вместо вопросика будет подставлено фактически переданное в метод число.
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class Math {
	public static final String ERROR_MESSAGE = "Expected non-negative number, got ";

	public static double sqrt(double value) throws IllegalAccessException {
		if (value < 0) {
			throw new IllegalArgumentException(ERROR_MESSAGE + value);
		}

		return java.lang.Math.sqrt(value);
	}
}
