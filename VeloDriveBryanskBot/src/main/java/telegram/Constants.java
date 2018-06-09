package telegram;

/**
 * Константы для работы с Telegram.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Constants {
	public static final String BOT_TOKEN = System.getenv().get("TELEGRAM_CONSTANT_BOT_TOKEN");
	public static final String BOT_NAME = System.getenv().get("TELEGRAM_CONSTANT_BOT_NAME");
	public static final long CHAT_ID = Long.parseLong(System.getenv().get("TELEGRAM_CONSTANT_CHAT_ID"));
	public static final long CHANNEL_ID = Long.parseLong(System.getenv().get("TELEGRAM_CONSTANT_CHANNEL_ID"));
	public static final String HASH_TAG = System.getenv().get("TELEGRAM_CONSTANT_HASH_TAG");

	public static final String PROXY_ADDRESS = System.getenv().get("TELEGRAM_CONSTANT_PROXY_ADDRESS");
	public static final int PROXY_PORT = Integer.parseInt(System.getenv().get("TELEGRAM_CONSTANT_PROXY_PORT"));

	public static final long TELEGRAM_SEND_DELAY = 1000L;
	public static final int TELEGRAM_CONNECTION_TIMEOUT = 60000;
}
