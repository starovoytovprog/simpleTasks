package processors;

import document.Product;
import document.Waybill;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование MultiThreadSimpleProcessor {@link MultiThreadSimpleProcessor}
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class FinalProcessorTest {

	static int[] test_sum = {280, 290, 180, 455, 215, 119, 86, 84, 1440, 873, 237, 165, 34};
	static int[] test_count = {20, 20, 100, 10, 20, 20, 20, 20, 5, 5, 10, 27, 1};
	static int test_result = 12454;

	/**
	 * Проверка поиска
	 */
	@Test
	public void simpleFindTest() {
		Waybill waybill = new Waybill();

		for (int i = 0; i < test_sum.length; i++) {
			waybill.add(new Product(test_count[i], test_sum[i]));
		}

		Waybill resultWaybill = FinalProcessor.find(waybill, test_result);
		assertEquals(resultWaybill.getSum(), test_result);
	}
}
