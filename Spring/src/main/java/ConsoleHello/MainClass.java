package ConsoleHello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		ApplicationContext context = new ClassPathXmlApplicationContext("ConsoleHello\\app-context.xml");
		MessageRenderer mr = context.getBean("renderer", MessageRenderer.class);
		mr.render();
	}
}