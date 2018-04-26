package telegram;

/**
 * Константы для работы с Telegram.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Constant {
    public static final String BOT_TOKEN = System.getenv().get("TELEGRAM_CONSTANT_BOT_TOKEN");
    public static final String BOT_NAME = System.getenv().get("TELEGRAM_CONSTANT_BOT_NAME");
    public static final long CHAT_ID = Long.parseLong(System.getenv().get("TELEGRAM_CONSTANT_BOT_NAME"));
}
