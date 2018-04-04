package lesson2.task4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson2.task4.BitArithmetic {@link BitArithmetic}
 *
 * @author Starovoytov
 * @since 03.04.2018
 */
public class BitArithmeticTest
{
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun()
	{
		assertEquals(BitArithmetic.flipBit(0, 1), 1);
	}
}
