package vk;

/**
 * Константы для работы с ВК.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Constants {
	public static final String POST_URL_PATTERN = "https://vk.com/wall";
	public static final int OWNER_ID = Integer.parseInt(System.getenv().get("VK_CONSTANT_OWNER_ID"));
	public static final int USER_ID = Integer.parseInt(System.getenv().get("VK_CONSTANT_USER_ID"));
	public static final String ACCESS_TOKEN = System.getenv().get("VK_CONSTANT_ACCESS_TOKEN");
	public static final int DELAY = Integer.parseInt(System.getenv().get("VK_CONSTANT_DELAY"));
	public static final int DELAY_FOR_ERROR = 360000;
}
