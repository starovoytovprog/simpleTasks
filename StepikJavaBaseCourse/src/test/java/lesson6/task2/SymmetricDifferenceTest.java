package lesson6.task2;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson6.task2.SymmetricDifference {@link SymmetricDifference}
 *
 * @author Starovoytov
 * @since 16.04.2018
 */
public class SymmetricDifferenceTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		Set<Integer> set1 = new HashSet<>();
		set1.add(1);
		set1.add(2);
		set1.add(3);

		Set<Integer> set2 = new HashSet<>();
		set2.add(0);
		set2.add(1);
		set2.add(2);

		Set<Integer> set3 = new HashSet<>();
		set3.add(3);
		set3.add(0);

		assertEquals(set3, SymmetricDifference.symmetricDifference(set1, set2));
	}
}
