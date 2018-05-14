package lesson6.task1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson6.task1.Pair {@link Pair}
 *
 * @author Starovoytov
 * @since 16.04.2018
 */
public class PairTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		Pair<Integer, String> pair = Pair.of(1, "hello");
		Integer i = pair.getFirst();
		String s = pair.getSecond();
		assertEquals(new Integer(1), i);
		assertEquals("hello", s);

		Pair<Integer, String> pair2 = Pair.of(1, "hello");
		assertTrue(pair.equals(pair2));
		assertEquals(pair.hashCode(), pair2.hashCode());
	}
}
