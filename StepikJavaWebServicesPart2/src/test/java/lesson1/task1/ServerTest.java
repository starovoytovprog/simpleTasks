package lesson1.task1;

import Utils.MainServerContainer;
import org.junit.Ignore;
import org.junit.Test;
import testUtils.HttpRequestSender;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson1.task1.Main {@link Main}, {@link UserCountServlet}
 *
 * @author Starovoytov
 * @since 05.07.2018
 */
@Ignore
public class ServerTest {
	/**
	 * Проверка результата выполнения метода
	 *
	 * @throws Exception исключения сервера
	 */
	@Test
	public void testRun() throws Exception {
		String testAddress = Main.PATH;
		String titleString = "10";
		Main.main(null);
		String responce = HttpRequestSender.sendEmptyGetRequest(testAddress);
		MainServerContainer.stop();

		assertTrue(responce.contains(titleString));
	}
}
