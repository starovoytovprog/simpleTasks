package main;

import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import vk.Manager;

/**
 * Точка входа в программу.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Main {
    public static void main(String[] args) throws TelegramApiRequestException {
        Manager.start();
    }
}
