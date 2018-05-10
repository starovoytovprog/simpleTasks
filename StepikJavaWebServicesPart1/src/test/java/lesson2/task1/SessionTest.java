package lesson2.task1;

import lesson2.task1.main.Main;
import lesson2.task1.servlets.SessionsServlet;
import org.junit.Test;
import utils.HttpRequestSender;
import utils.MainServerContainer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson2.task1.main.Main {@link Main}, {@link SessionsServlet}
 *
 * @author Starovoytov
 * @since 10.05.2018
 */
public class SessionTest
{
	/**
	 * Проверка результата выполнения метода
	 *
	 * @throws Exception исключения сервера
	 */
	@Test
	public void testRun() throws Exception
	{
		String testValue = "{\"login\":\"admin\",\"pass\":\"admin\",\"email\":\"admin\"}";
		String testAddress = "api/v1/sessions";
		Map<String, String> parameters = new HashMap<>();
		parameters.put("login", "admin");
		parameters.put("pass", "admin");
		Main.main(null);

		String responce = HttpRequestSender.sendPostRequest(testAddress, parameters);
		assertTrue(responce.contains(testValue));

		MainServerContainer.stop();
	}
}
