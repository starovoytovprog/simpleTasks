package interfaces.web;

import document.Waybill;
import loader.StringLoader;
import processors.FinalProcessor;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет обработки сообщений на поиск.
 *
 * @author Starovoytov
 * @since 04.07.2018
 */
public class CombinatoricServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String data = request.getParameter("data");
		int sum = Integer.parseInt(request.getParameter("sum"));
		Waybill result = FinalProcessor.find(StringLoader.loadFromBadString(data), sum);

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(result.toLine());
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
