package processors;

import document.Waybill;

/**
 * Простой процессор
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class SimpleProcessor {

	/**
	 * Поиск решения
	 *
	 * @param waybill исходная накладная
	 * @param sum     искомая сумма
	 * @return искомая накладная
	 */
	public static Waybill simpleFind(Waybill waybill, int sum) {
		int maxCount = (int) Math.pow(2, waybill.getProductCount());
		return simpleFindPart(waybill, sum, 1, maxCount);
	}

	/**
	 * Поиск решения в диапазоне масок
	 *
	 * @param waybill   исходная накладная
	 * @param sum       искомая сумма
	 * @param startMask начальное значение маски
	 * @param endMask   граница маски
	 * @return искомая накладная
	 */
	public static Waybill simpleFindPart(Waybill waybill, int sum, int startMask, int endMask) {
		if (waybill.getSum() < sum) {
			return null;
		}

		for (int i = startMask; i < endMask; i++) {
			Waybill w = waybill.getWaybillWithMask(i);
			if (w.getSumWithMax(sum) == sum) {
				return w;
			}
		}

		return null;
	}
}
