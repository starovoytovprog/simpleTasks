package lesson5.task1;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson5.task1.CheckSumHelper {@link CheckSumHelper}
 *
 * @author Starovoytov
 * @since 12.04.2018
 */
public class CheckSumHelperTest {
	/**
	 * Проверка результата выполнения метода
	 *
	 * @throws IOException Исключения в InputStream
	 */
	@Test
	public void testRun() throws IOException {
		byte[] byteArray = {0x33, 0x45, 0x01};
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
		assertEquals(CheckSumHelper.checkSumOfStream(byteArrayInputStream), 71);

		byte[] byteArray2 = {};
		ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(byteArray2);
		assertEquals(CheckSumHelper.checkSumOfStream(byteArrayInputStream2), 0);
	}
}
