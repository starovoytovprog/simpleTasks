package interfaces.web;

/**
 * Константы для работы сервера.
 *
 * @author Starovoytov
 * @since 04.07.2018
 */
public class Constants {
	public static final int PORT = Integer.parseInt(System.getenv().get("COMBINATORICS_SERVICE_PORT"));
	public static final String ADDRESS = "combinatoricsSearch";
}
