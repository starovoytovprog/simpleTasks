package ConsoleHello;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование вывода сообщения, используя связывание Spring
 *
 * @author Starovoytov
 * @since 05.12.2017
 */
public class SpringDITest
{
	private static final String TEST_OUT_STRING = "Hello World!\r\n";

	/**
	 * Тестирование вывода hello world с использованием связывания Spring
	 */
	@Test
	public void springHelloWorldTest()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("ConsoleHello\\app-context.xml");
		MessageRenderer mr = context.getBean("renderer", MessageRenderer.class);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);

		mr.render();

		System.out.flush();
		System.setOut(old);
		assertTrue(baos.toString().equals(TEST_OUT_STRING));
	}
}
