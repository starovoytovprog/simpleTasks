package interfaces.web;

import document.Waybill;
import loader.StringLoader;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static interfaces.web.Constants.ADDRESS;
import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson2.task1.main.Main {@link MainServer}
 *
 * @author Starovoytov
 * @since 04.07.2018
 */
public class MainServerTest {
	private static final int TEST_PORT = 991;
	private static final String SERVER = "http://localhost:" + TEST_PORT + "/";

	/**
	 * Отправляет post-запрос на адрес и получает результат
	 *
	 * @param address    Адрес запрашиваемого документа
	 * @param parameters Мапа параметров запроса
	 * @return Контент страницы ответа
	 * @throws Exception Ошибки запроса к серверу
	 */
	private static String sendPostRequest(String address, Map<String, String> parameters) throws IOException {
		HttpURLConnection connection = getConnection(address, "POST");
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
		return connection;
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
		BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		final StringBuilder sb = new StringBuilder();
		input.lines().forEach(line -> sb.append(line));

		connection.disconnect();
		return sb.toString();
	}

	@Test
	public void sendDataTest() {
		MainServer server = new MainServer(TEST_PORT);
		server.start();
		Map<String, String> testData = new HashMap<>();
		testData.put("data", "1;2;3;4;5;6;7;8;9");
		testData.put("sum", "10");
		String result = "";

		try {
			result = sendPostRequest(ADDRESS, testData);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		server.stop();

		assertEquals(10, getSumFromResponce(result));
	}

	private int getSumFromResponce(String responce) {
		responce = responce
		.replaceAll("]]", "")
		.replaceAll("\\[", "")
		.replaceAll("]", System.lineSeparator())
		.replaceAll(": ", ";")
		.replaceAll("count = ", "")
		.replaceAll("price = ", "");

		return StringLoader.load(responce).getSum();
	}
}
