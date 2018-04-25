package utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Класс-оболочка для управления сервером
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class MainServerContainer
{
	private static final Server server = new Server(8080);

	private MainServerContainer()
	{
	}

	public static void setHandler(ServletContextHandler handler)
	{
		server.setHandler(handler);
	}

	public static void start() throws Exception
	{
		server.start();
	}

	public static void stop() throws Exception
	{
		server.stop();
	}
}
