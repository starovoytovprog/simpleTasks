package mainpack;

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
}
