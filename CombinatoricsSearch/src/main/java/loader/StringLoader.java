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

	private final static String R = "\r";
	private final static String N = "\n";
	private final static String EMPTY = "";
	private static final String SEPARATOR = ";";

	/**
	 * Загрузить накладную из неизвестной строки
	 *
	 * @param source строка
	 * @return накладная
	 */
	public static Waybill loadFromBadString(String source) {
		source = source.replaceAll(R, EMPTY).replaceAll(N, System.lineSeparator());
		if (source.contains(System.lineSeparator())) {
			return load(source);
		}

		return loadSumOnly(source);
	}

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

	/**
	 * Загрузить накладную из строки сумм
	 *
	 * @param source строка
	 * @return накладная
	 */
	public static Waybill loadSumOnly(String source) {
		Waybill waybill = new Waybill();

		Arrays.stream(source.split(SEPARATOR)).forEach(line -> {
			if (line.length() > 0) {
				waybill.add(new Product(1, Integer.parseInt(line)));
			}
		});

		return waybill;
	}
}
