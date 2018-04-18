package lesson5.task4;

import TestUtils.ConsoleHelper;
import org.junit.Test;

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
	 * @throws Exception может бросить исключения
	 */
	@Test
	public void testRun() throws Exception
	{
		ConsoleHelper.testMain("1 2" + System.lineSeparator() + "3" + System.lineSeparator(), "6.000000", Main.class);
	}
}
