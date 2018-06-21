package loader;

import document.Product;
import document.Waybill;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование StringLoader {@link StringLoader}
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class StringLoaderTest {
	/**
	 * Проверка загрузки простой накладной
	 */
	@Test
	public void loadTest() {
		String testString = "1;1" + System.lineSeparator() + "2;2" + System.lineSeparator() + "3;3";
		Waybill waybill = new Waybill();
		waybill.add(new Product(1, 1));
		waybill.add(new Product(2, 2));
		waybill.add(new Product(3, 3));
		testLoad(testString, waybill);
	}

	/**
	 * Проверка загрузки простой накладной
	 */
	@Test
	public void onlySumLoadTest() {
		String testString = "1;2;3;4;5;6;7;8;9";
		Waybill waybill = new Waybill();
		waybill.add(new Product(1, 1));
		waybill.add(new Product(1, 2));
		waybill.add(new Product(1, 3));
		waybill.add(new Product(1, 4));
		waybill.add(new Product(1, 5));
		waybill.add(new Product(1, 6));
		waybill.add(new Product(1, 7));
		waybill.add(new Product(1, 8));
		waybill.add(new Product(1, 9));

		testLoadOnlySum(testString, waybill);
	}

	public void testLoad(String source, Waybill etalon) {
		Waybill waybill = StringLoader.load(source);
		assertEquals(etalon, waybill);
	}

	public void testLoadOnlySum(String source, Waybill etalon) {
		Waybill waybill = StringLoader.loadSumOnly(source);
		assertEquals(etalon, waybill);
	}
}
