package utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

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
	private static final String REQUEST_METOD_POST = "POST";

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
	 * Отправляет пустой get-запрос на адрес и получает результат
	 *
	 * @param address Адрес запрашиваемого документа
	 * @param parameters Мапа параметров запроса
	 * @return Контент страницы ответа
	 * @throws Exception Ошибки запроса к серверу
	 */
	public static String sendPostRequest(String address, Map<String, String> parameters) throws Exception
	{
		HttpURLConnection connection = getConnection(address, REQUEST_METOD_POST);
		connection.setDoOutput(true);

		OutputStream outputStream = connection.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
		bufferedWriter.write(getParametersString(parameters));
		bufferedWriter.close();

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
		connection.connect();
		BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		final StringBuilder sb = new StringBuilder();
		input.lines().forEach(line -> sb.append(line));

		return sb.toString();
	}

	/**
	 * Преобразует параметры в строку
	 *
	 * @param parameters входные параметры
	 * @return строка параметров
	 */
	private static String getParametersString(Map<String, String> parameters)
	{
		if (parameters == null || parameters.isEmpty())
		{
			return "";
		}

		String result = "";

		for (String key : parameters.keySet())
		{
			if (!result.isEmpty())
			{
				result += "&";
			}

			result += key + "=" + parameters.get(key);
		}

		return result;
	}
}
