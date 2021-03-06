package document;

/**
 * Товар - элемент накладной
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class Product {

	private final int count;
	private final int price;

	/**
	 * Конструктор
	 *
	 * @param count количество товара в накладной
	 * @param price стоимость товара
	 */
	public Product(int count, int price) {
		this.count = count;
		this.price = price;
	}

	@Override
	public boolean equals(Object product2) {
		if (this == product2) {
			return true;
		}

		if (product2 == null || getClass() != product2.getClass()) {
			return false;
		}

		Product product = (Product) product2;

		if (count != product.count) {
			return false;
		}

		return price == product.price;
	}

	@Override
	public int hashCode() {
		int result = count;
		result = 31*result + price;
		return result;
	}

	/**
	 * Получить сумму товаров
	 *
	 * @return сумма товаров
	 */
	public int getSum() {
		return count*price;
	}

	/**
	 * Получить цену товара
	 *
	 * @return цена товара
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Получить количество товара
	 *
	 * @return количество товара
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Получить сумму определённого количества
	 *
	 * @param count количество
	 * @return сумма
	 */
	public int getSumWithCount(int count) {
		if (count > this.count || count <= 0) {
			throw new IllegalArgumentException("Incorrect parameter count: " + count);
		}

		return count*price;
	}

	@Override
	public String toString() {
		return "count = " + count + ": price = " + price;
	}
}
