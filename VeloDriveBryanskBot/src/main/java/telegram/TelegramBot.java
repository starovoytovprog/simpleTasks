package telegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static telegram.Constant.BOT_NAME;
import static telegram.Constant.BOT_TOKEN;

/**
 * Бот для отправки сообщений в группу.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class TelegramBot extends TelegramLongPollingBot {

    private TelegramBot(DefaultBotOptions options) {
        super(options);
    }

    public static void init() {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            DefaultBotOptions options = new DefaultBotOptions();
            //HttpHost proxy = new HttpHost("75.151.213.85", 3366, "https");
            //options.setHttpProxy(proxy);
            botsApi.registerBot(new TelegramBot(options));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
