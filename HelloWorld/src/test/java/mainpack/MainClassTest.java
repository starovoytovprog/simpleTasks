package mainpack;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование HelloWorld {@link HelloWorldMain}
 *
 * @author starovoytov
 * @created 29.11.2017
 * @$Author$
 * @$Revision$
 */
public class MainClassTest
{
	private static final String HELLO_WORLD_OUT = "Hello World!\r\n";

	@Test
	public void HelloWorldMainTest()
	{
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
