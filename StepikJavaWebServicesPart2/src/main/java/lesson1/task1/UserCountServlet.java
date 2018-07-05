package lesson1.task1;

import utils.BaseHttpServlet;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Запуск сервлета, возвращающего разрешённое количество пользователей
 *
 * @author Starovoytov
 * @since 05.07.2018
 */
public class UserCountServlet extends BaseHttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().println("10");
		response.setContentType(HTTP_CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
