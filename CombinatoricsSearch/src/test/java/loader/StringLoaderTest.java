package loader;

import document.Product;
import document.Waybill;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование StringLoader {@link StringLoader}
 *
 * @author Starovoytov
 * @since 29.11.2017
 */
public class StringLoaderTest {
	/**
	 * Проверка загрузки простой накладной
	 */
	@Test
	public void HelloWorldMainTest() {
		String testString = "1;1" + System.lineSeparator() + "2;2" + System.lineSeparator() + "3;3";
		Waybill waybill = new Waybill();
		waybill.add(new Product(1, 1));
		waybill.add(new Product(2, 2));
		waybill.add(new Product(3, 3));
		testLoad(testString, waybill);
	}

	public void testLoad(String source, Waybill etalon) {
		Waybill waybill = StringLoader.load(source);
		assertEquals(etalon, waybill);
	}
}
