package lesson2.task1;

import Utils.MainServerContainer;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Запуск сервлета ресурсной системы
 *
 * @author Starovoytov
 * @since 05.07.2018
 */
public class Main {
	public static final String PATH = "resources";

	public static void main(String[] args) throws Exception {
		ResourceServlet servlet = new ResourceServlet();
		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		contextHandler.addServlet(new ServletHolder(servlet), "/" + PATH);

		MainServerContainer.setHandler(contextHandler);
		MainServerContainer.start();
	}
}
