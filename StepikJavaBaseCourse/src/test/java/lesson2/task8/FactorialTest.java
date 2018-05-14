package lesson2.task8;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson2.task8.Factorial {@link Factorial}
 *
 * @author Starovoytov
 * @since 04.04.2018
 */
public class FactorialTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		assertEquals(Factorial.factorial(1), BigInteger.valueOf(1));
		assertEquals(Factorial.factorial(3), BigInteger.valueOf(6));
	}
}
