package lesson2.task1;

import lesson2.task1.main.Main;
import lesson2.task1.servlets.SignUpServlet;
import org.junit.Test;
import testUtils.HttpRequestSender;
import utils.MainServerContainer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson2.task1.main.Main {@link Main}, {@link SignUpServlet}
 *
 * @author Starovoytov
 * @since 11.05.2018
 */
public class SignInSignUpTest {
	/**
	 * Проверка результата выполнения метода
	 *
	 * @throws Exception исключения сервера
	 */
	@Test
	public void testRun() throws Exception {
		Main.main(null);

		String signUpAddress = "signup";
		String signInAddress = "signin";
		String error401 = "Server returned HTTP response code: 401 for URL:";
		Map<String, String> parameters = new HashMap<>();
		parameters.put("login", "testlogin");
		parameters.put("password", "testpassword");

		try {
			HttpRequestSender.sendPostRequest(signInAddress, parameters);
		}
		catch (Exception ex) {
			assertTrue(ex.getMessage().contains(error401));
		}

		String responce = HttpRequestSender.sendPostRequest(signUpAddress, parameters);
		assertTrue(responce.contains("Ok!"));

		responce = HttpRequestSender.sendPostRequest(signInAddress, parameters);
		assertTrue(responce.contains("Authorized:"));

		MainServerContainer.stop();
	}
}
