package ConsoleHello;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование стартового класса для консольного "hello world" приложения {@link MainClass}
 *
 * @author Starovoytov
 * @since 05.12.2017
 */
public class MainClassTest
{
	private static final String HELLO_WORLD_OUT = "Hello World!\r\n";

	/**
	 * Сравнивание вывода сообщения в консоль с эталонной строкой
	 */
	@Test
	public void HelloWorldMainTest()
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		MainClass.main(null);
		System.out.flush();
		System.setOut(old);
		assertTrue(baos.toString().equals(HELLO_WORLD_OUT));
	}
}
