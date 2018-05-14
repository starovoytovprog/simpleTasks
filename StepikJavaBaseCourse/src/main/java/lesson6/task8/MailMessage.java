package lesson6.task8;

/**
 * Почтовое сообщение
 *
 * @author Starovoytov
 * @since 18.04.2018
 */
public class MailMessage implements MailServiceAcceptable {
	private final String from;
	private final String to;
	private final String content;

	public MailMessage(String from, String to, String content) {
		this.from = from;
		this.to = to;
		this.content = content;
	}

	public String getFrom() {
		return from;
	}

	@Override
	public String getTo() {
		return to;
	}

	@Override
	public String getContent() {
		return content;
	}
}
