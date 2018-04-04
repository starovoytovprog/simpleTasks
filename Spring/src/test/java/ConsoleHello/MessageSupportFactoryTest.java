package ConsoleHello;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование вывода сообщения, используя фабрику {@link MessageSupportFactory}
 *
 * @author Starovoytov
 * @since 05.12.2017
 */
public class MessageSupportFactoryTest {
    private static final String TEST_OUT_STRING = "Hello World!" + System.lineSeparator();

    /**
     * Тестирование вывода hello world с использованием фабрики
     */
    @Test
    public void messageFactoryTest() {
        MessageSupportFactory.getInstance();
        MessageRenderer mr = MessageSupportFactory.getInstance().getRenderer();
        MessageProvider mp = MessageSupportFactory.getInstance().getProvider();
        mr.setMessageProvider(mp);

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
