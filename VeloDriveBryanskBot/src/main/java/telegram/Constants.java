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

	public static final long TELEGRAM_SEND_DELAY = Long.parseLong(System.getenv().get("TELEGRAM_CONSTANT_TELEGRAM_SEND_DELAY"));
	public static final int TELEGRAM_CONNECTION_TIMEOUT = Integer.parseInt(System.getenv().get("TELEGRAM_CONSTANT_TELEGRAM_CONNECTION_TIMEOUT"));
	public static final long OWNER_ID = Long.parseLong(System.getenv().get("TELEGRAM_CONSTANT_OWNER_ID"));
	public static final long RECONNECT_TIMER = Long.parseLong(System.getenv().get("TELEGRAM_CONSTANT_RECONNECT_TIMER"));
	public static final long RECONNECT_DELIMITER = Long.parseLong(System.getenv().get("TELEGRAM_CONSTANT_RECONNECT_DELIMITER"));
}
