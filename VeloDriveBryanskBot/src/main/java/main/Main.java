package main;

import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
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

        try {
            TelegramBot.init();
        } catch (TelegramApiRequestException ex) {
            System.exit(-1);
        }
        Manager.start();
    }
}
