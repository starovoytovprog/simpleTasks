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

    private static Thread scanThread = null;

    /**
     * Запустить поток-обработчик новостей.
     */
    public static void start() {

        scanThread = new Thread(() -> {
            while (true) {
                scan();

                try {
                    Thread.currentThread().sleep(Constants.DELAY);
                } catch (InterruptedException ex) {
                    return;
                }

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
    private static void scan() {
        try {
            List<String> linkList = Consumer.getNewPostsLinks();
            linkList.stream().forEach(link -> {
                TelegramBot.sendMessage(link);
                delay();
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void delay() {
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
