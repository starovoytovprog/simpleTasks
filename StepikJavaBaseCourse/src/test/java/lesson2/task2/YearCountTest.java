package lesson2.task2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson2.task2.YearCount {@link YearCount}
 *
 * @author Starovoytov
 * @since 02.04.2018
 */
public class YearCountTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		assertEquals(YearCount.leapYearCount(1), 0);
		assertEquals(YearCount.leapYearCount(4), 1);
		assertEquals(YearCount.leapYearCount(100), 24);
	}
}
