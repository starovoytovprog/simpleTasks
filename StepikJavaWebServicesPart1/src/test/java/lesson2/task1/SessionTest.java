package lesson2.task1;

import lesson2.task1.main.Main;
import lesson2.task1.servlets.SessionsServlet;
import org.junit.Test;
import testUtils.HttpRequestSender;
import utils.MainServerContainer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson2.task1.main.Main {@link Main}, {@link SessionsServlet}
 *
 * @author Starovoytov
 * @since 10.05.2018
 */
public class SessionTest {
	/**
	 * Проверка результата выполнения метода
	 *
	 * @throws Exception исключения сервера
	 */
	@Test
	public void testRun() throws Exception {
		String testValue = "{\"login\":\"admin\",\"pass\":\"admin\",\"email\":\"admin\"}";
		String testAddress = "api/v1/sessions";
		String error401 = "Server returned HTTP response code: 401 for URL:";
		String logout = "Goodbye!";
		Map<String, String> parameters = new HashMap<>();
		parameters.put("login", "admin");
		parameters.put("pass", "admin");
		Main.main(null);

		try {
			HttpRequestSender.sendEmptyGetRequest(testAddress);
		}
		catch (IOException ex) {
			assertTrue(ex.getMessage().contains(error401));
		}

		String responce = HttpRequestSender.sendPostRequest(testAddress, parameters);
		assertTrue(responce.contains(testValue));

		responce = HttpRequestSender.sendEmptyGetRequest(testAddress);
		assertTrue(responce.contains(testValue));

		responce = HttpRequestSender.sendDeleteRequest(testAddress, null);
		assertTrue(responce.contains(logout));

		try {
			HttpRequestSender.sendEmptyGetRequest(testAddress);
		}
		catch (IOException ex) {
			assertTrue(ex.getMessage().contains(error401));
		}

		MainServerContainer.stop();
	}
}
