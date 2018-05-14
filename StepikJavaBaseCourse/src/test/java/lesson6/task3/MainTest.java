package lesson6.task3;

import TestUtils.ConsoleHelper;
import org.junit.Test;

/**
 * Тестирование lesson6.task3.Main {@link Main}
 *
 * @author Starovoytov
 * @since 16.04.2018
 */
public class MainTest {
	/**
	 * Проверка результата выполнения метода
	 *
	 * @throws Exception может бросить исключения
	 */
	@Test
	public void testRun() throws Exception {
		ConsoleHelper.testMain("1 2 3 4 5 6 7", "6 4 2", Main.class);
	}
}
