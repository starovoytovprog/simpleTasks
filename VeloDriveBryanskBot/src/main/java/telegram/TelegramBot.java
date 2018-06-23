package telegram;

import message.Message;
import message.MessageManager;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.MessageEntity;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.generics.BotSession;

import static telegram.Constants.*;

/**
 * Бот для отправки сообщений в группу.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class TelegramBot extends TelegramLongPollingBot {

	private static TelegramBot currentBot = null;
	private static BotSession botSession = null;
	private static TelegramBotsApi botsApi = new TelegramBotsApi();

	private TelegramBot(DefaultBotOptions options) {
		super(options);
	}

	/**
	 * Инициализировать бота
	 *
	 * @throws TelegramApiRequestException ошибка запуска бота
	 */
	public static void init() throws TelegramApiRequestException {
		ApiContextInitializer.init();
		register();
		new Reconnector().start();
	}

	private static void register() throws TelegramApiRequestException {
		try {
			unregister();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			Thread.currentThread().sleep(RECONNECT_DELIMITER);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		currentBot = new TelegramBot(getConfigureOptions());
		botSession = botsApi.registerBot(currentBot);
		System.out.println("telegram bot started");
	}

	private static void unregister() throws TelegramApiRequestException {
		if (currentBot != null) {
			try {
				botSession.stop();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			currentBot.clearWebhook();
			currentBot.onClosing();
			System.out.println("telegram bot stopped");
		}
	}

	/**
	 * Сформировать конфигкрацию бота
	 *
	 * @return конфигурация
	 */
	private static DefaultBotOptions getConfigureOptions() {
		DefaultBotOptions options = new DefaultBotOptions();

		if (PROXY_ADDRESS != null && !PROXY_ADDRESS.isEmpty() && PROXY_PORT > 0) {
			HttpHost proxy = new HttpHost(PROXY_ADDRESS, PROXY_PORT);

			RequestConfig conf = RequestConfig.custom().setProxy(proxy).setAuthenticationEnabled(false).setConnectTimeout(TELEGRAM_CONNECTION_TIMEOUT).setConnectionRequestTimeout(TELEGRAM_CONNECTION_TIMEOUT).build();

			options.setRequestConfig(conf);
		}

		return options;
	}

	/**
	 * Отправка сообщения с помощью актуального бота
	 *
	 * @param message сообщение для отправки
	 */
	public static void sendMessageInBot(Message message) {
		currentBot.sendMessage(message);
	}

	/**
	 * Отправка сообщения в группу
	 *
	 * @param message
	 * @param groupId
	 */
	private void sendMessage(String message, long groupId) {
		try {
			SendMessage sendMessageObject = new SendMessage();
			sendMessageObject.setText(message);
			sendMessageObject.setChatId(groupId);
			currentBot.sendApiMethod(sendMessageObject);
		}
		catch (Exception ex) {
			MessageManager.getInstance().messageProcess(message, groupId);
			ex.printStackTrace();
		}
	}

	/**
	 * Отправка сообщения
	 *
	 * @param message Сообщение для отправки
	 */
	public void sendMessage(Message message) {
		if (message.getMessageString() != null) {
			sendMessage(message.getMessageString(), message.getChatId());
		}
	}

	@Override
	public void onUpdateReceived(Update update) {
		try {
			if (update.getMessage().getChatId() == OWNER_ID) {
				MessageManager.getInstance().messageProcess(update.getMessage().getText(), OWNER_ID);
			}
			if (update.getMessage() != null) {
				if (update.getMessage().getChatId() == CHAT_ID) {
					if (update.getMessage().getEntities() != null) {
						if (!update.getMessage().getEntities().isEmpty()) {
							for (MessageEntity entity : update.getMessage().getEntities()) {
								if (entity.getType().equals("hashtag") && entity.getText().equals("#" + HASH_TAG)) {
									MessageManager.getInstance().messageProcess("Новая покатушка в чате!\n" + update.getMessage().getText(), false);
									return;
								}
							}
						}
					}
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return BOT_NAME;
	}

	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}

	/**
	 * Костыль для работы с прокси. Через определённое время отключает бота, ждёт некоторый интервал, подключает заново.
	 * Необходимо для прокси, которые не хотят, чтобы к ним висело продолжительное подключение.
	 */
	private static class Reconnector extends Thread {
		@Override
		public void run() {
			if (RECONNECT_TIMER > 0) {
				boolean err = false;
				while (true) {
					try {
						if (!err) {
							try {
								Thread.currentThread().sleep(RECONNECT_TIMER);
							}
							catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						register();
						err = false;
					}
					catch (TelegramApiRequestException e) {
						e.printStackTrace();
						err = true;
					}
					catch (Exception e) {
						e.printStackTrace();
						err = true;
					}
				}
			}
		}
	}
}
