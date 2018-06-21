package document;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование Product {@link Product}
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class ProductTest {
	/**
	 * Проверка подсчёта суммы с ограничением
	 */
	@Test
	public void getSumWithCountTest() {
		Product product = new Product(10, 1);

		for (int i = 1; i <= 10; i++) {
			assertEquals(product.getSumWithCount(i), i);
		}

		boolean isExeption = false;
		try {
			product.getSumWithCount(0);
		}
		catch (IllegalArgumentException ex) {
			isExeption = true;
		}
		assertTrue(isExeption);

		isExeption = false;
		try {
			product.getSumWithCount(-5);
		}
		catch (IllegalArgumentException ex) {
			isExeption = true;
		}
		assertTrue(isExeption);
		isExeption = false;
		try {
			product.getSumWithCount(11);
		}
		catch (IllegalArgumentException ex) {
			isExeption = true;
		}
		assertTrue(isExeption);
		isExeption = false;
		try {
			product.getSumWithCount(100);
		}
		catch (IllegalArgumentException ex) {
			isExeption = true;
		}
		assertTrue(isExeption);
	}
}
