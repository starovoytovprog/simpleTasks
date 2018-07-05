package Utils;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;

/**
 * Класс-оболочка для управления сервером
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class MainServerContainer {
	private static final Server server = new Server(8080);

	private MainServerContainer() {
	}

	public static void setHandler(Handler handler) {
		server.setHandler(handler);
	}

	public static void start() throws Exception {
		server.start();
		System.out.println("Server started");
	}

	public static void stop() throws Exception {
		server.stop();
		System.out.println("Server stoped");
	}
}
