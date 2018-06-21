package lesson4.task1.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

/**
 * Реализация веб-сокета
 *
 * @author Starovoytov
 * @since 20.06.2018
 */
@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {

	private Session session;

	/**
	 * Открытие сокета
	 *
	 * @param session сессия
	 */
	@OnWebSocketConnect
	public void onOpen(Session session) {
		this.session = session;
	}

	/**
	 * Обработка входящего сообщения
	 *
	 * @param data входящее сообщение
	 */
	@OnWebSocketMessage
	public void onMessage(String data) {
		try {
			session.getRemote().sendString(data);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
