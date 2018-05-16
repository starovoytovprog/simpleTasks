package lesson2.task9;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson2.task9.Merge {@link Merge}
 *
 * @author Starovoytov
 * @since 04.04.2018
 */
public class MergeTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		int[] a1 = {0, 2, 2};
		int[] a2 = {1, 3};
		int[] a3 = {0, 1, 2, 2, 3};

		assertTrue(Arrays.equals(Merge.mergeArrays(a1, a2), a3));
	}
}
