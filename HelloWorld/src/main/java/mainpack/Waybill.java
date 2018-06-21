package mainpack;

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
}
