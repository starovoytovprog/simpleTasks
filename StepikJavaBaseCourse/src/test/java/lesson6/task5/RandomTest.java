package lesson6.task5;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson6.task5.Random {@link Random}
 *
 * @author Starovoytov
 * @since 17.04.2018
 */
public class RandomTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		int[] resultValues = Random.pseudoRandomStream(13).limit(9).toArray();
		int[] thrueValues = {13, 16, 25, 62, 384, 745, 502, 200, 0};

		assertEquals(Arrays.toString(resultValues), Arrays.toString(thrueValues));
	}
}
