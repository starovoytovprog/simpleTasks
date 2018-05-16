package message;

/**
 * Обработчик сообщений из контакта
 *
 * @author Starovoytov
 * @since 30.04.2018
 */
public interface MessageConsumer {

	/**
	 * Обработка сообщений
	 *
	 * @param message ссылка на сообщение
	 * @param inChat  отправлять сообщение в чат
	 */
	void messageProcess(String message, boolean inChat);
}
