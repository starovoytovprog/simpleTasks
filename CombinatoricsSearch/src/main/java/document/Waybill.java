package document;

import java.util.ArrayList;
import java.util.List;

/**
 * Накладная
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
@SuppressWarnings("unchecked")
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

	/**
	 * Получить сумму с ограничением
	 *
	 * @param max ограничение
	 * @return сумма
	 */
	public int getSumWithMax(int max) {
		int sum = 0;

		for (Product product : products) {
			sum += product.getSum();
			if (sum > max) break;
		}

		return sum;
	}

	/**
	 * Сформировать накладную по маске
	 *
	 * @param mask маска
	 * @return результирующая накладная
	 */
	public Waybill getWaybillWithMask(int mask) {
		Waybill waybill = new Waybill();

		for (int i = 0; i < products.size(); i++) {
			if (mask%2 == 1) {
				waybill.add(products.get(i));
			}

			mask = mask>>1;

			if (mask == 0) break;
		}

		return waybill;
	}

	/**
	 * Получить количество товаров
	 *
	 * @return количество товаров
	 */
	public int getProductCount() {
		return products.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[" + System.lineSeparator());

		for (Product product : products) {
			sb.append("    ");
			sb.append(product);
			sb.append(System.lineSeparator());
		}

		sb.append(']');
		return sb.toString();
	}

	public String toLine() {
		StringBuilder sb = new StringBuilder("[");

		for (Product product : products) {
			sb.append("[");
			sb.append(product);
			sb.append("]");
		}

		sb.append(']');
		return sb.toString();
	}

	/**
	 * Получить модифицированную накладную
	 *
	 * @param modParameter коэффициент модификации
	 * @return модифицированная накладная
	 */
	public Waybill getSimpleModified(int modParameter) {

		Waybill waybill = new Waybill();

		int allCount = 0;

		for (Product product : products) {
			allCount += product.getCount();
			allCount--;
		}

		if (allCount < modParameter) return waybill;

		for (Product product : products) {
			if (modParameter == 0) {
				waybill.add(product);
			}
			else if (modParameter >= product.getCount()) {
				modParameter -= product.getCount() - 1;
				waybill.add(new Product(1, product.getPrice()));
			}
			else {
				waybill.add(new Product(product.getCount() - modParameter, product.getPrice()));
				modParameter = 0;
			}
		}

		return waybill;
	}
}
