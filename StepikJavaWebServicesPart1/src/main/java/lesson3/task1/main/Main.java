package lesson3.task1.main;

import lesson2.task1.accounts.AccountService;
import lesson2.task1.accounts.UserProfile;
import lesson2.task1.servlets.SessionsServlet;
import lesson2.task1.servlets.SignInServlet;
import lesson2.task1.servlets.SignUpServlet;
import lesson2.task1.servlets.UsersServlet;
import lesson3.task1.accounts.DbAccountService;
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
		AccountService accountService = new DbAccountService();

		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		contextHandler.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
		contextHandler.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
		contextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
		contextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setResourceBase(String.valueOf(Main.class.getClassLoader().getResource("templates/lesson2")));

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[]{resource_handler, contextHandler});

		MainServerContainer.setHandler(handlers);
		MainServerContainer.start();
	}
}