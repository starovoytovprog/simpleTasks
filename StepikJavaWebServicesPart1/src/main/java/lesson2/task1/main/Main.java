package lesson2.task1.main;

import lesson2.task1.accounts.AccountService;
import lesson2.task1.accounts.UserProfile;
import lesson2.task1.servlets.SessionsServlet;
import lesson2.task1.servlets.UsersServlet;
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
 * @since 08.05.2018
 */
public class Main
{
	public static void main(String[] args) throws Exception
	{
		AccountService accountService = new AccountService();
		accountService.addNewUser(new UserProfile("admin"));
		accountService.addNewUser(new UserProfile("test"));

		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		contextHandler.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
		contextHandler.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");

		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setResourceBase(String.valueOf(Main.class.getClassLoader().getResource("templates/lesson2")));

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[]{resource_handler, contextHandler});

		MainServerContainer.setHandler(handlers);
		MainServerContainer.start();
	}
}