package main;

import telegram.TelegramBot;
import vk.Manager;

/**
 * Точка входа в программу.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Main {
    public static void main(String[] args) {
        TelegramBot.init();
        Manager.start();
    }
}
