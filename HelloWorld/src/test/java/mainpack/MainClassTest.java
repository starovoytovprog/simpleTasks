package mainpack;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование HelloWorld {@link HelloWorldMain}
 *
 * @author Starovoytov
 * @since 29.11.2017
 */
public class MainClassTest {
	private static final String HELLO_WORLD_OUT = "Hello World!" + System.lineSeparator();

	/**
	 * Сравнивание вывода сообщения в консоль с эталонной строкой
	 */
	@Test
	public void HelloWorldMainTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		HelloWorldMain.main(null);
		System.out.flush();
		System.setOut(old);
		assertTrue(baos.toString().equals(HELLO_WORLD_OUT));
	}
}
