package telegram;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import vk.MessageConsumer;

import static telegram.Constants.*;

/**
 * Бот для отправки сообщений в группу.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class TelegramBot extends TelegramLongPollingBot implements MessageConsumer {

    private static TelegramBot currentBot = null;

    private TelegramBot(DefaultBotOptions options) {
        super(options);
    }

    /**
     * Инициализировать бота
     *
     * @return инициализированный бот
     */
    public static TelegramBot init() {

        if (currentBot == null) {
            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();
            try {
                currentBot = new TelegramBot(getConfigureOptions());
                botsApi.registerBot(currentBot);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        return currentBot;
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

            RequestConfig conf = RequestConfig.custom()
                    .setProxy(proxy)
                    .setAuthenticationEnabled(false)
                    .build();

            options.setRequestConfig(conf);
        }

        return options;
    }

    /**
     * Отправка сообщения в группу
     *
     * @param message
     */
    private void sendMessage(String message) {
        try {
            SendMessage sendMessageObject = new SendMessage();
            sendMessageObject.setText(message);
            sendMessageObject.setChatId(CHAT_ID);
            currentBot.sendApiMethod(sendMessageObject);
        } catch (Exception ex) {

        }
        System.out.println(message);
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getChatId());
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
     * Обработка ссылки на сообщение
     *
     * @param message ссылка на сообщение
     */
    @Override
    public void messageProcess(String message) {
        sendMessage("Новый пост в группе!\n" + message);
    }
}
