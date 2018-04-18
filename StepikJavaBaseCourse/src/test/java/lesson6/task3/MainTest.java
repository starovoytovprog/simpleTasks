package lesson6.task3;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson6.task3.Main {@link Main}
 *
 * @author Starovoytov
 * @since 16.04.2018
 */
public class MainTest
{
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun()
	{
		testMain("1 2 3 4 5 6 7", "6 4 2");
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

		Main.main(null);

		System.setIn(systemInputBuffer);
		System.setOut(systemOutputBuffer);

		assertEquals(etalon, resultStream.toString());
	}
}
