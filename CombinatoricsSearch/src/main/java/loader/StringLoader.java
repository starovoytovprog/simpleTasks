package loader;

import document.Product;
import document.Waybill;

import java.util.Arrays;

/**
 * Загрузчик накладной из строки
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class StringLoader {

	private static final String SEPARATOR = ";";

	/**
	 * Загрузить накладную из строки
	 *
	 * @param source строка
	 * @return накладная
	 */
	public static Waybill load(String source) {
		Waybill waybill = new Waybill();

		Arrays.stream(source.split(System.lineSeparator())).forEach(line -> {
			if (line.length() > 0) {
				waybill.add(new Product(Integer.parseInt(line.split(SEPARATOR)[0]), Integer.parseInt(line.split(SEPARATOR)[1])));
			}
		});

		return waybill;
	}
}
