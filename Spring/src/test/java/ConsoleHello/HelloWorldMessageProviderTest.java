package ConsoleHello;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование провайдера сообщений Hello World {@link HelloWorldMessageProvider}
 *
 * @author Starovoytov
 * @since 05.12.2017
 */
public class HelloWorldMessageProviderTest {
    private static final String HELLO_WORLD_OUT = "Hello World!";

    /**
     * Проверяет возвращённое сообщение по эталону
     */
    @Test
    public void getMessageFromHelloWorldProviderTest() {
        assertTrue(new HelloWorldMessageProvider().getMessage().equals(HELLO_WORLD_OUT));
    }
}
