package document;

import java.util.ArrayList;
import java.util.List;

/**
 * Накладная
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class Waybill {
	private final List<Product> products;

	/**
	 * Конструктор по умолчанию
	 */
	public Waybill() {
		products = new ArrayList();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Waybill waybill = (Waybill) o;

		if (products == waybill.products) {
			return true;
		}

		if (products == null) {
			return false;
		}

		return products.size() == waybill.products.size() && products.containsAll(waybill.products);
	}

	@Override
	public int hashCode() {
		return products != null ? products.hashCode() : 0;
	}

	/**
	 * Получиь сумму накладной
	 *
	 * @return сумма накладной
	 */
	public int getSum() {
		int sum = 0;

		for (Product product : products) {
			sum += product.getSum();
		}

		return sum;
	}

	/**
	 * Добавить товар
	 *
	 * @param product товар
	 */
	public void add(Product product) {
		products.add(product);
	}
}
