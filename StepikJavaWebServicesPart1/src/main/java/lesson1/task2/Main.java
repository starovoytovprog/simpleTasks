package lesson1.task2;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import utils.MainServerContainer;

/**
 * Запуск задания
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Main {
	public static void main(String[] args) throws Exception {
		MirrorServlet helloWorldServlet = new MirrorServlet();

		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		contextHandler.addServlet(new ServletHolder(helloWorldServlet), "/mirror");

		MainServerContainer.setHandler(contextHandler);
		MainServerContainer.start();
	}
}
