package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Вспомогательный класс для отправки http-запросов и получения ответа.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class HttpRequestSender
{
	private static final String SERVER = "http://localhost:8080/";
	private static final String REQUEST_METOD_GET = "GET";

	/**
	 * Отправляет пустой get-запрос на адрес и получает результат
	 *
	 * @param address Адрес запрашиваемого документа
	 * @return Контент страницы ответа
	 * @throws Exception Ошибки запроса к серверу
	 */
	public static String sendEmptyGetRequest(String address) throws Exception
	{
		HttpURLConnection connection = getConnection(address, REQUEST_METOD_GET);
		return getStringResponce(connection);
	}

	/**
	 * Установка соединения
	 *
	 * @param address Адрес документа
	 * @param requestMetod Метод запроса
	 * @return Коннектор
	 * @throws IOException Ошибки подключения
	 */
	private static HttpURLConnection getConnection(String address, String requestMetod) throws IOException
	{
		URL url = new URL(SERVER + address);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(requestMetod);
		connection.connect();
		return connection;
	}

	/**
	 * Получить тело ответа
	 *
	 * @param connection Коннектор
	 * @return Тело ответа в виде строки
	 * @throws IOException Ошибки ввода-вывода
	 */
	private static String getStringResponce(HttpURLConnection connection) throws IOException
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		final StringBuilder sb = new StringBuilder();
		input.lines().forEach(line -> sb.append(line));

		return sb.toString();
	}
}
