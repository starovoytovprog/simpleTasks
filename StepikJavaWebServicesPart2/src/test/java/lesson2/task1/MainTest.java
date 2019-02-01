package lesson2.task1;

import Utils.MainServerContainer;
import org.junit.Test;
import testUtils.HttpRequestSender;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Тест ресурсного сервлета
 */
public class MainTest {
	@Test
	public void mainTest() throws Exception {
		String testAddress = Main.PATH;
		String titleString = "name:testName; age: 123";
		Main.main(null);
		Map<String, String> params = new HashMap<>();
		params.put("path", new File((MainTest.class.getResource("/lesson2/test.xml").toURI())).getCanonicalPath());
		String response = HttpRequestSender.sendPostRequest(testAddress, params);
		MainServerContainer.stop();

		assertTrue(response.contains(titleString));
	}
}