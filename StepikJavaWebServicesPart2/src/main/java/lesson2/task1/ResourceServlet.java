package lesson2.task1;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Ресурсный сервлет
 *
 * @author Starovoytov
 * @since 29.01.2019
 */
public class ResourceServlet extends HttpServlet {

	private final ResourceServerMBean resourceServer;

	public ResourceServlet(ResourceServerMBean resourceServer) {
		this.resourceServer = resourceServer;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getParameter("path");
		if (path != null) {
			try {
				resourceServer.loadData(request.getParameter("path"));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		response.getWriter().println("hello");
		response.setContentType("http");
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
