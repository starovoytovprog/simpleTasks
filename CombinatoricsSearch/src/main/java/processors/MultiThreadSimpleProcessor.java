package processors;

import document.Waybill;

import java.util.ArrayList;

/**
 * Простой многопоточный процессор
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class MultiThreadSimpleProcessor {

	private final static int THREAD_COUNT = 5;
	private final int sum;
	private Waybill waybill;
	private Waybill result = new Waybill();

	/**
	 * Конструктор
	 *
	 * @param waybill накладная
	 * @param sum     искомая сумма
	 */
	public MultiThreadSimpleProcessor(Waybill waybill, int sum) {
		this.waybill = waybill;
		this.sum = sum;
	}

	/**
	 * Установить накладную
	 *
	 * @param waybill накладная
	 */
	public void setWaybill(Waybill waybill) {
		this.waybill = waybill;
	}

	/**
	 * Поиск решения
	 *
	 * @return искомая накладная
	 */
	public Waybill simpleFind() {
		int maxCount = (int) Math.pow(2, waybill.getProductCount());
		int step = maxCount/THREAD_COUNT;

		ArrayList<FindThread> threads = new ArrayList<>(THREAD_COUNT);

		for (int i = 0; i < THREAD_COUNT; i++) {
			FindThread thread = new FindThread(i*step, (i + 1)*step);
			thread.start();
			threads.add(thread);
		}

		for (FindThread thread : threads) {
			try {
				thread.join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private class FindThread extends Thread {
		private final int start;
		private final int end;

		public FindThread(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			if (waybill.getSum() < sum) {
				return;
			}

			for (int i = start; i < end; i++) {

				if (result.getSum() > 0) return;

				Waybill w = waybill.getWaybillWithMask(i);
				if (w.getSumWithMax(sum) == sum) {
					synchronized (result) {
						result = w;
					}
					return;
				}
			}
		}
	}
}
