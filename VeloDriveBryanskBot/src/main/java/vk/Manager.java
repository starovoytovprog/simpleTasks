package vk;

import telegram.TelegramBot;

import java.util.List;

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
     * Остановить поток-обработчик новостей.
     */
    public static void stop() {
        if (scanThread != null) {
            scanThread.interrupt();
        }
    }

    /**
     * Обработка последних новостей
     */
    private static void scan(Collector collector) {
        try {
            List<String> linkList = collector.getNewPostsLinks();
            linkList.stream().forEach(link -> {
                CONSUMER.messageProcess(link);
                delay();
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Ожидание между выполнениями запросов к API
     */
    private static void delay() {
        try {
            Thread.currentThread().sleep(Constants.DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
