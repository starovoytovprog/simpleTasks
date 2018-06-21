package processors;

import document.Waybill;

/**
 * Простой процессор
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class SimpleProcessor {

	public static Waybill simpleFind(Waybill waybill, int sum) {

		if (waybill.getSum() < sum) {
			return null;
		}

		int maxCount = (int) Math.pow(2, waybill.getProductCount());

		for (int i = 1; i < maxCount; i++) {
			Waybill w = waybill.getWaybillWithMask(i);
			if (w.getSumWithMax(sum) == sum) {
				return w;
			}
		}

		return null;
	}
}
