package lesson1.task1;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Запуск сервлета, возвращающего разрешённое количество пользователей
 *
 * @author Starovoytov
 * @since 05.07.2018
 */
public class UserCountServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().println("10");
		response.setContentType("http");
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
