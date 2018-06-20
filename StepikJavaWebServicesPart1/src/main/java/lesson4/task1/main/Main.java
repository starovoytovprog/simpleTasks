package lesson4.task1.main;

import lesson4.task1.chat.ChatServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import utils.MainServerContainer;

/**
 * Запуск задания
 *
 * @author Starovoytov
 * @since 20.06.2018
 */
public class Main {

	public static void main(String[] args) throws Exception {
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

		context.addServlet(new ServletHolder(new ChatServlet()), "/chat");

		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirectoriesListed(true);
		resource_handler.setResourceBase("public_html");

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[]{resource_handler, context});
		MainServerContainer.setHandler(handlers);
		MainServerContainer.start();
	}
}
