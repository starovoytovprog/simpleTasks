package lesson3.task2;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson3.task2.ComplexNumber {@link ComplexNumber}
 *
 * @author Starovoytov
 * @since 05.04.2018
 */
public class ComplexNumberTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		ComplexNumber n1 = new ComplexNumber(1, 1);
		ComplexNumber n2 = new ComplexNumber(1, 1);
		ComplexNumber n3 = new ComplexNumber(1, 2);
		ComplexNumber n4 = new ComplexNumber(2, 1);
		ComplexNumber n5 = new ComplexNumber(2, 2);

		assertTrue(n1.equals(n2));
		assertTrue(n1.hashCode() == n2.hashCode());
		assertFalse(n1.equals(n3));
		assertFalse(n1.hashCode() == n3.hashCode());
		assertFalse(n1.equals(n4));
		assertFalse(n1.hashCode() == n4.hashCode());
		assertFalse(n1.equals(n5));
		assertFalse(n1.hashCode() == n5.hashCode());
		assertFalse(n3.equals(n4));
		assertFalse(n3.hashCode() == n4.hashCode());
	}
}
