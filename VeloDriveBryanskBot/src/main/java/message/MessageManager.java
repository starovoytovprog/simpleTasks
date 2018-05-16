package message;

import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import telegram.Constants;
import telegram.TelegramBot;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Менеджер отправки сообщений
 *
 * @author Starovoytov
 * @since 16.05.2018
 */
public class MessageManager implements MessageConsumer {

	private static final MessageManager INSTANCE = new MessageManager();
	private static final BlockingQueue<Message> MESSAGES = new LinkedBlockingQueue<>();

	static {
		try {
			Thread messageSender = new MessageSender();
			messageSender.start();
		}
		catch (TelegramApiRequestException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private MessageManager() {}

	/**
	 * Получить экземпляр менеджера отправки сообщений.
	 *
	 * @return экземпляр менеджера отправки сообщений.
	 */
	public static MessageManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Принять сообщение для отправки
	 *
	 * @param message ссылка на сообщение
	 * @param inChat  отправлять сообщение в чат
	 */
	@Override
	public void messageProcess(String message, boolean inChat) {
		try {
			MESSAGES.put(new Message(message, Constants.CHANNEL_ID));
			if (inChat) {
				MESSAGES.put(new Message(message, Constants.CHAT_ID));
			}
		}
		catch (InterruptedException e) {}
	}

	/**
	 * Класс для отправки сообщений в отдельном потоке
	 */
	private static class MessageSender extends Thread {
		private final TelegramBot bot = TelegramBot.init();

		private MessageSender() throws TelegramApiRequestException {}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.currentThread().sleep(Constants.TELEGRAM_SEND_DELAY);
					bot.sendMessage(MESSAGES.take());
				}
				catch (InterruptedException e) {}
			}
		}
	}
}
