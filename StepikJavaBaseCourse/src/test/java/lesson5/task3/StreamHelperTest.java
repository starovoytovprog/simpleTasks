package lesson5.task3;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson5.task3.StreamHelper {@link StreamHelper}
 *
 * @author Starovoytov
 * @since 12.04.2018
 */
public class StreamHelperTest {
	/**
	 * Проверка результата выполнения метода
	 *
	 * @throws IOException Исключения в InputStream
	 */
	@Test
	public void testRun() throws IOException {
		byte[] testArray = {48, 49, 50, 51};
		assertEquals(StreamHelper.readAsString(new ByteArrayInputStream(testArray), StandardCharsets.US_ASCII), "0123");
	}
}
