package lesson2.task6;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson2.task6.IsPowerOfTwo {@link IsPowerOfTwo}
 *
 * @author Starovoytov
 * @since 03.04.2018
 */
public class IsPowerOfTwoTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		assertFalse(IsPowerOfTwo.isPowerOfTwo(0));
		assertTrue(IsPowerOfTwo.isPowerOfTwo(1));
		assertTrue(IsPowerOfTwo.isPowerOfTwo(-2));
		assertFalse(IsPowerOfTwo.isPowerOfTwo(31));
	}
}
