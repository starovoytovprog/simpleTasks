package message;

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
		new MessageSender().start();
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
			if (Constants.CHANNEL_ID != 0) MESSAGES.put(new Message(message, Constants.CHANNEL_ID));
			if (inChat && Constants.CHAT_ID != 0) {
				MESSAGES.put(new Message(message, Constants.CHAT_ID));
			}
		}
		catch (InterruptedException e) {}
	}

	/**
	 * Принять сообщение для отправки
	 *
	 * @param message ссылка на сообщение
	 * @param chatId  чат
	 */
	public void messageProcess(String message, long chatId) {
		System.out.println("resieve message for send");
		try {
			MESSAGES.put(new Message(message, chatId));
		}
		catch (InterruptedException e) {
			System.out.println("error resieve message for send " + e.getMessage());
		}
	}

	/**
	 * Класс для отправки сообщений в отдельном потоке
	 */
	private static class MessageSender extends Thread {
		private MessageSender() {
			TelegramBot.init();
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.currentThread().sleep(Constants.TELEGRAM_SEND_DELAY);
					TelegramBot.sendMessageInBot(MESSAGES.take());
				}
				catch (InterruptedException e) {}
			}
		}
	}
}
