package vk;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiTooManyException;
import com.vk.api.sdk.exceptions.ClientException;
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

    private static final MessageConsumer CONSUMER = TelegramBot.init();
    private static Thread scanThread = null;

    /**
     * Запустить поток-обработчик новостей.
     */
    public static void start() {
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
                CONSUMER.messageProcess(link, "Новый пост в группе!");
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
