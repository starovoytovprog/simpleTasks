package ConsoleHello;

/**
 * Стартовый класс для консольного "hello world" приложения
 *
 * @author Starovoytov
 * @since 05.12.2017
 */
public class MainClass
{
	/**
	 * Вывод "Hello World!" в консоль
	 *
	 * @param args Не используется
	 */
	public static void main(String[] args)
	{
		MessageRenderer mr = new StandardOutMessageRenderer();
		MessageProvider mp = new HelloWorldMessageProvider();
		mr.setMessageProvider(mp);
		mr.render();
	}
}