package lesson1.task1;

import Utils.MainServerContainer;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Запуск сервлета, возвращающего разрешённое количество пользователей
 *
 * @author Starovoytov
 * @since 05.07.2018
 */
public class Main {

	public static final String PATH = "admin";

	public static void main(String[] args) throws Exception {
		UserCountServlet helloWorldServlet = new UserCountServlet();

		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		contextHandler.addServlet(new ServletHolder(helloWorldServlet), "/" + PATH);

		MainServerContainer.setHandler(contextHandler);
		MainServerContainer.start();
	}
}
