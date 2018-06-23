package lesson1.task2;

import org.junit.Test;
import testUtils.HttpRequestSender;
import utils.MainServerContainer;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson1.task2.Main {@link Main}, {@link MirrorServlet}
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class MirrorTest {
	/**
	 * Проверка результата выполнения метода
	 *
	 * @throws Exception исключения сервера
	 */
	@Test
	public void testRun() throws Exception {
		String testValue = "testValue";
		String testAddress = "mirror?key=" + testValue;
		Main.main(null);
		String responce = HttpRequestSender.sendEmptyGetRequest(testAddress);
		MainServerContainer.stop();

		assertTrue(responce.contains(testValue));
	}
}
