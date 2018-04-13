package lesson5.task2;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson5.task2.Main {@link Main}
 *
 * @author Starovoytov
 * @since 12.04.2018
 */
public class MainTest
{
	/**
	 * Проверка результата выполнения метода
	 * @throws IOException Исключения в InputStream/OutputStream
	 */
	@Test
	public void testRun() throws IOException
	{
		InputStream systemInputBuffer = System.in;
		PrintStream systemOutputBuffer = System.out;

		byte[] byteArray = {65, 13, 10, 10, 13};
		InputStream testStrim = new ByteArrayInputStream(byteArray);
		System.setIn(testStrim);

		ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
		PrintStream testPrint = new PrintStream(resultStream);
		System.setOut(testPrint);

		Main.main(null);

		System.setIn(systemInputBuffer);
		System.setOut(systemOutputBuffer);

		byte[] etalon = {65, 10, 10, 13};
		assertTrue(Arrays.equals(etalon, resultStream.toByteArray()));
	}
}
