package lesson6.task7;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson6.task7.Main {@link Main}
 *
 * @author Starovoytov
 * @since 18.04.2018
 */
public class MainTest
{
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun()
	{
		testMain("Мама мыла-мыла-мыла раму!", "мыла" + System.lineSeparator() + "мама" + System.lineSeparator() + "раму" + System.lineSeparator());
		testMain("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim."
			, "consectetur" + System.lineSeparator() +
				"faucibus" + System.lineSeparator() +
				"ipsum" + System.lineSeparator() +
				"lorem" + System.lineSeparator() +
				"adipiscing" + System.lineSeparator() +
				"amet" + System.lineSeparator() +
				"dolor" + System.lineSeparator() +
				"eget" + System.lineSeparator() +
				"elit" + System.lineSeparator() +
				"mi" + System.lineSeparator());
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
