package lesson2.task8;

import java.math.BigInteger;

/**
 * Реализуйте метод, вычисляющий факториал заданного натурального числа.
 * Факториал N вычисляется как 1⋅2⋅...⋅N.
 * Поскольку это очень быстро растущая функция, то даже для небольших N вместимости типов int и long очень скоро не хватит. Поэтому будем использовать BigInteger.
 * <p>
 * Sample Input 1: 1; Sample Output 1: 1
 * Sample Input 2: 3; Sample Output 2: 6
 *
 * @author Starovoytov
 * @since 04.04.2018
 */
public class Factorial {
	/**
	 * Calculates factorial of given <code>value</code>.
	 *
	 * @param value positive number
	 * @return factorial of <code>value</code>
	 */
	public static BigInteger factorial(int value) {
		BigInteger result = BigInteger.ONE;
		for (int i = 2; i <= value; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}
}
