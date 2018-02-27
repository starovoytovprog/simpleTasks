package ConsoleHello;

import java.io.IOException;
import java.util.Properties;

/**
 * Фабрика, создающая объекты для управления сообщениями
 *
 * @author Starovoytov
 * @since 05.12.2017
 */
public class MessageSupportFactory {
    private static final String PROPERTIES_FILE = "hello_world.properties";
    private static final String PROPERTIES_RENDERER = "renderer.class";
    private static final String PROPERTIES_PROVIDER = "provider.class";
    private static final MessageSupportFactory instance = new MessageSupportFactory();

    private MessageProvider provider;
    private MessageRenderer renderer;

    private MessageSupportFactory() {
        Properties messageProperties = new Properties();
        try {

            messageProperties.load(MessageSupportFactory.class.getResourceAsStream(PROPERTIES_FILE));
            provider = (MessageProvider) Class.forName(messageProperties.getProperty(PROPERTIES_PROVIDER)).newInstance();
            renderer = (MessageRenderer) Class.forName(messageProperties.getProperty(PROPERTIES_RENDERER)).newInstance();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получить экземпляр фабрики
     *
     * @return Фабрика объектов управления сообщениями
     */
    public static MessageSupportFactory getInstance() {
        return instance;
    }

    /**
     * Получить созданный фабрикой провайдер
     *
     * @return созданный провайдер
     */
    public MessageProvider getProvider() {
        return provider;
    }

    /**
     * Получить созданный фабрикой рендерер
     *
     * @return созданный рендерер
     */
    public MessageRenderer getRenderer() {
        return renderer;
    }
}
