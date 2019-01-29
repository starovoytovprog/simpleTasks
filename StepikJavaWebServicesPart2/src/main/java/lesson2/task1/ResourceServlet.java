package lesson2.task1;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Ресурсный сервлет
 */
public class ResourceServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().println("hello");
		response.setContentType("http");
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
