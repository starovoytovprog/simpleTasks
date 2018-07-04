package interfaces.web;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import static interfaces.web.Constants.ADDRESS;

/**
 * Сервер для приёма сообщений на обработку.
 *
 * @author Starovoytov
 * @since 04.07.2018
 */
public class MainServer {
	private final Server server;

	/**
	 * Конструктор сервера
	 *
	 * @param port порт сервера
	 */
	public MainServer(int port) {
		server = new Server(port);
		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		contextHandler.addServlet(new ServletHolder(new CombinatoricServlet()), "/" + ADDRESS);

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[]{contextHandler});
		server.setHandler(handlers);
	}

	/**
	 * Запустить сервер
	 */
	public void start() {
		try {
			server.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Остановить сервер
	 */
	public void stop() {
		try {
			server.stop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
