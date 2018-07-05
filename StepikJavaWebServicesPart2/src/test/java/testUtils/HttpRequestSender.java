package testUtils;

import java.io.*;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Вспомогательный класс для отправки http-запросов и получения ответа.
 *
 * @author Starovoytov
 * @since 05.07.2018
 */
public class HttpRequestSender {
	static final String COOKIES_HEADER = "Set-Cookie";
	private static final String SERVER = "http://localhost:8080/";
	private static final String REQUEST_METOD_GET = "GET";
	private static final String REQUEST_METOD_POST = "POST";
	private static final String REQUEST_METOD_DELETE = "DELETE";
	static CookieManager msCookieManager = new CookieManager();

	/**
	 * Отправляет пустой get-запрос на адрес и получает результат
	 *
	 * @param address Адрес запрашиваемого документа
	 * @return Контент страницы ответа
	 * @throws Exception Ошибки запроса к серверу
	 */
	public static String sendEmptyGetRequest(String address) throws Exception {
		//HttpURLConnection connection = getConnection(address, REQUEST_METOD_GET);
		return sendRequestWithParameters(address, null, REQUEST_METOD_GET);
	}

	/**
	 * Отправляет post-запрос на адрес и получает результат
	 *
	 * @param address    Адрес запрашиваемого документа
	 * @param parameters Мапа параметров запроса
	 * @return Контент страницы ответа
	 * @throws Exception Ошибки запроса к серверу
	 */
	public static String sendPostRequest(String address, Map<String, String> parameters) throws Exception {
		return sendRequestWithParameters(address, parameters, REQUEST_METOD_POST);
	}

	/**
	 * Отправляет delete-запрос на адрес и получает результат
	 *
	 * @param address    Адрес запрашиваемого документа
	 * @param parameters Мапа параметров запроса
	 * @return Контент страницы ответа
	 * @throws Exception Ошибки запроса к серверу
	 */
	public static String sendDeleteRequest(String address, Map<String, String> parameters) throws Exception {
		return sendRequestWithParameters(address, parameters, REQUEST_METOD_DELETE);
	}

	private static String sendRequestWithParameters(String address, Map<String, String> parameters, String metod) throws IOException {
		HttpURLConnection connection = getConnection(address, metod);

		if (parameters != null) {
			connection.setDoOutput(true);
			OutputStream outputStream = connection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
			bufferedWriter.write(getParametersString(parameters));
			bufferedWriter.close();
		}

		return getStringResponce(connection);
	}

	/**
	 * Установка соединения
	 *
	 * @param address      Адрес документа
	 * @param requestMetod Метод запроса
	 * @return Коннектор
	 * @throws IOException Ошибки подключения
	 */
	private static HttpURLConnection getConnection(String address, String requestMetod) throws IOException {
		URL url = new URL(SERVER + address);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(requestMetod);

		if (msCookieManager.getCookieStore().getCookies().size() > 0) {
			connection.setRequestProperty("Cookie", getStringFromCookie(msCookieManager.getCookieStore().getCookies()));
		}

		return connection;
	}

	private static String getStringFromCookie(List<HttpCookie> cookies) {
		String result = "";

		for (HttpCookie cookie : cookies) {
			result += cookie.toString() + ";";
		}

		return result;
	}

	/**
	 * Получить тело ответа
	 *
	 * @param connection Коннектор
	 * @return Тело ответа в виде строки
	 * @throws IOException Ошибки ввода-вывода
	 */
	private static String getStringResponce(HttpURLConnection connection) throws IOException {
		connection.connect();

		Map<String, List<String>> headerFields = connection.getHeaderFields();
		List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);

		if (cookiesHeader != null) {
			for (String cookie : cookiesHeader) {
				msCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
			}
		}

		BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		final StringBuilder sb = new StringBuilder();
		input.lines().forEach(line -> sb.append(line));

		connection.disconnect();
		return sb.toString();
	}

	/**
	 * Преобразует параметры в строку
	 *
	 * @param parameters входные параметры
	 * @return строка параметров
	 */
	private static String getParametersString(Map<String, String> parameters) {
		if (parameters == null || parameters.isEmpty()) {
			return "";
		}

		String result = "";

		for (String key : parameters.keySet()) {
			if (!result.isEmpty()) {
				result += "&";
			}

			result += key + "=" + parameters.get(key);
		}

		return result;
	}
}
