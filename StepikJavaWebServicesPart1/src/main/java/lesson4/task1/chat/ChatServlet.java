package lesson4.task1.chat;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

/**
 * Реализация сервлета
 *
 * @author Starovoytov
 * @since 20.06.2018
 */
@WebServlet(name = "WebSocketServlet", urlPatterns = {"/chat"})
public class ChatServlet extends WebSocketServlet {

	private final static int LOGOUT_TIME = 10*60*1000;

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
		factory.setCreator((req, resp) -> new ChatWebSocket());
	}
}
