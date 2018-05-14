package lesson1.task2;

import utils.BaseHttpServlet;
import utils.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Написать сервлет, который будет обрабатывать запросы на /mirror
 * При получении GET запроса с параметром key=value сервлет должен вернуть в response строку содержащую value.
 * Например, при GET запросе /mirror?key=hello сервер должен вернуть страницу, на которой есть слово "hello".
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class MirrorServlet extends BaseHttpServlet {
	private static final String MIRROR_PAGE = "lesson1" + FILE_RESOURCE_SEPARATOR + "mirrorPage.html";
	private static final String PARAMETET_NAME = "key";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> pageVariables = createPageVariablesMap(request);

		response.getWriter().println(PageGenerator.instance().getPage(MIRROR_PAGE, pageVariables));
		response.setContentType(HTTP_CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	protected Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
		Map<String, Object> pageVariables = new HashMap<>();

		if (request.getParameterMap().containsKey(PARAMETET_NAME)) {
			pageVariables.put(PARAMETET_NAME, request.getParameter(PARAMETET_NAME));
		}
		else {
			pageVariables.put(PARAMETET_NAME, "");
		}

		return pageVariables;
	}
}
