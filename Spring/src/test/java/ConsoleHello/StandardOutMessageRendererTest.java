package ConsoleHello;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование стандартного рендерера сообщений {@link StandardOutMessageRenderer}
 *
 * @author Starovoytov
 * @since 05.12.2017
 */
public class StandardOutMessageRendererTest {
    private static final String TEST_STRING = "test";

    /**
     * Проверяет вывод сообщения рендерером
     */
    @Test
    public void standatdRendererTest() {
        StandardOutMessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(new MessageProvider() {
            @Override
            public String getMessage() {
                return TEST_STRING;
            }
        });

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        renderer.render();

        System.out.flush();
        System.setOut(old);

        assertTrue(baos.toString().equals(TEST_STRING + "\r\n"));
    }
}
