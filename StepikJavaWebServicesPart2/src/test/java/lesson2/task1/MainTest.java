package lesson2.task1;

import Utils.MainServerContainer;
import org.junit.Test;
import testUtils.HttpRequestSender;

import static org.junit.Assert.*;

/**
 * Тест ресурсного сервлета
 */
public class MainTest {
	@Test
	public void mainTest() throws Exception {
		String testAddress = Main.PATH;
		String titleString = "hello";
		Main.main(null);
		String response = HttpRequestSender.sendEmptyGetRequest(testAddress);
		MainServerContainer.stop();

		assertTrue(response.contains(titleString));
	}
}