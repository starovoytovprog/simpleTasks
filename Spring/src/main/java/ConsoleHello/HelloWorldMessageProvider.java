package ConsoleHello;

/**
 * Провайдер сообщений "hello world"
 *
 * @author Starovoytov
 * @since 05.12.2017
 */
public class HelloWorldMessageProvider implements MessageProvider
{
	private static final String HELLO_WORLD_STRING = "Hello World!";

	/**
	 * Получить сообщение провайдера
	 *
	 * @return сообщение "Hello World!"
	 */

	public String getMessage()
	{
		return HELLO_WORLD_STRING;
	}
}
