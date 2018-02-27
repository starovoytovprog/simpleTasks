package ConsoleHello;

/**
 * Стандартный рендерер сообщений
 *
 * @author Starovoytov
 * @since 05.12.2017
 */
public class StandardOutMessageRenderer implements MessageRenderer {
    private MessageProvider provider;

    /**
     * Установить провайдера сообщений
     */
    public void setMessageProvider(MessageProvider provider) {
        this.provider = provider;
    }

    /**
     * Вывод сообщений
     */
    public void render() {
        System.out.println(provider.getMessage());
    }
}
