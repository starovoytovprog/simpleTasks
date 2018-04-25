package lesson1.task1;

import org.junit.Test;
import utils.HttpRequestSender;
import utils.MainServerContainer;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson1.task1.Main {@link Main}, {@link HelloWorldServlet}
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class HelloWorldTest
{
	/**
	 * Проверка результата выполнения метода
	 * @throws Exception исключения сервера
	 */
	@Test
	public void testRun() throws Exception
	{
		String testAddress = "testAddress";
		String titleString = "<title>Hello world page</title>";
		Main.main(null);
		String responce = HttpRequestSender.sendEmptyGetRequest(testAddress);
		MainServerContainer.stop();

		assertTrue(responce.contains(testAddress));
		assertTrue(responce.contains(titleString));
	}
}
