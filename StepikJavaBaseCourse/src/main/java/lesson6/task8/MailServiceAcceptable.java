package lesson6.task8;

/**
 * Интерфейс обработки почтовым сервисом
 *
 * @author Starovoytov
 * @since 18.04.2018
 */
public interface MailServiceAcceptable {
	String getTo();

	Object getContent();
}
