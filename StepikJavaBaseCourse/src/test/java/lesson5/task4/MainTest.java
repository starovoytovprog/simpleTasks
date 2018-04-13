package lesson5.task4;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson5.task4.Main {@link Main}
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
		testMain("1 2" + System.lineSeparator() + "3" + System.lineSeparator(), "6.000000");
	}

	private void testMain(String in, String etalon)
	{
		InputStream systemInputBuffer = System.in;
		PrintStream systemOutputBuffer = System.out;

		InputStream testStrim = new ByteArrayInputStream(in.getBytes());
		System.setIn(testStrim);

		ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
		PrintStream testPrint = new PrintStream(resultStream);
		System.setOut(testPrint);

		try
		{
			Main.main(null);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		System.setIn(systemInputBuffer);
		System.setOut(systemOutputBuffer);

		assertEquals(etalon, resultStream.toString());
	}
}
