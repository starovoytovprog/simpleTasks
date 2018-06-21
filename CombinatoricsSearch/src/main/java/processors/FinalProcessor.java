package processors;

import document.Waybill;

/**
 * Процессор для полного цикла поиска
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class FinalProcessor {

	/**
	 * Поиск решения
	 *
	 * @param waybill исходная накладная
	 * @param sum     искомая сумма
	 * @return искомая накладная
	 */
	public static Waybill find(Waybill waybill, int sum) {
		MultiThreadSimpleProcessor processor = new MultiThreadSimpleProcessor(waybill, sum);

		Waybill result = processor.simpleFind();
		int k = 0;

		while (result.getSumWithMax(sum) != sum) {
			k++;
			Waybill modWaybill = waybill.getSimpleModified(k);
			if (modWaybill.getSum() == 0) break;
			processor.setWaybill(modWaybill);
			result = processor.simpleFind();
		}

		return result;
	}
}
