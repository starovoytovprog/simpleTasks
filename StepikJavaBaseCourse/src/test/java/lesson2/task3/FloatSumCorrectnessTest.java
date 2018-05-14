package lesson2.task3;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson2.task3.FloatSumCorrectness {@link FloatSumCorrectness}
 *
 * @author Starovoytov
 * @since 03.04.2018
 */
public class FloatSumCorrectnessTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		assertTrue(FloatSumCorrectness.doubleExpression(0.1, 0.2, 0.3));
	}
}
