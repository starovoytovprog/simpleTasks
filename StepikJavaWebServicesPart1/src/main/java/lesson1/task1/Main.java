package lesson1.task1;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import utils.MainServerContainer;

/**
 * Пробный запуск jetty
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Main
{
	public static void main(String[] args) throws Exception
	{
		HelloWorldServlet helloWorldServlet = new HelloWorldServlet();

		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		contextHandler.addServlet(new ServletHolder(helloWorldServlet), "/*");

		MainServerContainer.setHandler(contextHandler);
		MainServerContainer.start();
	}
}
