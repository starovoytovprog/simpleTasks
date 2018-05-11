package vk;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiTooManyException;
import com.vk.api.sdk.exceptions.ClientException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import telegram.TelegramBot;

import java.util.List;

import static vk.Constants.DELAY;
import static vk.Constants.DELAY_FOR_ERROR;

/**
 * Управление сборкой новостей.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Manager {

    private static MessageConsumer CONSUMER;
    private static Thread scanThread = null;

    /**
     * Запустить поток-обработчик новостей.
     *
     * @throws TelegramApiRequestException ошибка старта
     */
    public static void start() throws TelegramApiRequestException {
        CONSUMER = TelegramBot.init();
        Collector collector = new Collector();

        scanThread = new Thread(() -> {
            while (true) {
                scan(collector);
                delay();
            }
        });

        scanThread.start();
    }

    /**
     * Обработка последних новостей
     */
    private static void scan(Collector collector) {
        try {
            List<String> linkList = collector.getNewPostsLinks();
            linkList.stream().forEach(link -> {
                CONSUMER.messageProcess(link, "Новый пост в группе!", true);
                delay();
            });
        } catch (ApiTooManyException e) {
            e.printStackTrace();
            delayTime(DELAY_FOR_ERROR);
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ожидание между выполнениями запросов к API
     */
    private static void delay() {
        delayTime(DELAY);
    }

    /**
     * Ожидание указанного времени
     */
    private static void delayTime(long delay) {
        try {
            Thread.currentThread().sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
