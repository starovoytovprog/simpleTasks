package message;

/**
 * Сообщение для отправки.
 *
 * @author Starovoytov
 * @since 16.05.2018
 */
public class Message {
	private final String messageString;
	private final long chatId;

	public Message(String messageString, long chatId) {
		this.messageString = messageString;
		this.chatId = chatId;
	}

	public String getMessageString() {
		return messageString;
	}

	public long getChatId() {
		return chatId;
	}
}
