package document;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование Waybill {@link Waybill}
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class WaybillTest {
	/**
	 * Проверка подсчёта суммы с ограничением
	 */
	@Test
	public void getSumWithMaxTest() {
		Waybill waybill = getTestWaybill(6, 1);

		assertEquals(waybill.getSum(), 60);
		assertEquals(waybill.getSumWithMax(31), 40);
		assertEquals(waybill.getSumWithMax(0), 10);
	}

	/**
	 * Проверка формирования накладной по маске
	 */
	@Test
	public void getWaybillWithMask() {
		Waybill waybill = getTestWaybill(10, 1);

		assertEquals(waybill.getWaybillWithMask(1), getTestWaybill(1, 1));
		assertEquals(waybill.getWaybillWithMask(2), getTestWaybill(1, 1));
		assertEquals(waybill.getWaybillWithMask(3), getTestWaybill(2, 1));
		assertEquals(waybill.getWaybillWithMask(4), getTestWaybill(1, 1));
		assertEquals(waybill.getWaybillWithMask(5), getTestWaybill(2, 1));
		assertEquals(waybill.getWaybillWithMask(6), getTestWaybill(2, 1));
		assertEquals(waybill.getWaybillWithMask(7), getTestWaybill(3, 1));
		assertEquals(waybill.getWaybillWithMask(8), getTestWaybill(1, 1));
		assertEquals(waybill.getWaybillWithMask(9), getTestWaybill(2, 1));
	}

	private Waybill getTestWaybill(int n, int count) {
		Waybill waybill = new Waybill();

		for (int i = 0; i < n; i++) {
			waybill.add(new Product(count, 10));
		}

		return waybill;
	}

	/**
	 * Проверка формирования модифицированной накладной
	 */
	@Test
	public void getModifiedWaybill() {
		Waybill waybill = new Waybill();
		waybill.add(new Product(10, 1));
		waybill.add(new Product(10, 1));
		waybill.add(new Product(10, 1));
		waybill.add(new Product(10, 1));

		Waybill testWaybill = new Waybill();
		testWaybill.add(new Product(5, 1));
		testWaybill.add(new Product(10, 1));
		testWaybill.add(new Product(10, 1));
		testWaybill.add(new Product(10, 1));
		assertEquals(testWaybill, waybill.getSimpleModified(5));

		testWaybill = new Waybill();
		testWaybill.add(new Product(1, 1));
		testWaybill.add(new Product(9, 1));
		testWaybill.add(new Product(10, 1));
		testWaybill.add(new Product(10, 1));
		assertEquals(testWaybill, waybill.getSimpleModified(10));

		testWaybill = new Waybill();
		testWaybill.add(new Product(1, 1));
		testWaybill.add(new Product(4, 1));
		testWaybill.add(new Product(10, 1));
		testWaybill.add(new Product(10, 1));
		assertEquals(testWaybill, waybill.getSimpleModified(15));

		testWaybill = new Waybill();
		testWaybill.add(new Product(1, 1));
		testWaybill.add(new Product(1, 1));
		testWaybill.add(new Product(1, 1));
		testWaybill.add(new Product(1, 1));
		assertEquals(testWaybill, waybill.getSimpleModified(36));

		testWaybill = new Waybill();
		assertEquals(testWaybill, waybill.getSimpleModified(45));
	}
}
