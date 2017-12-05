package ConsoleHello;

/**
 * Интерфейс рендерера сообщений
 *
 * @author Starovoytov
 * @since 05.12.2017
 */
public interface MessageRenderer
{
	/**
	 * Установить провайдера сообщений
	 */
	void setMessageProvider(MessageProvider provider);

	/**
	 * Вывод сообщений
	 */
	void render();
}
