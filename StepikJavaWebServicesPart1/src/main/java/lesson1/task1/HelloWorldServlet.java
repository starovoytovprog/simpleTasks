package lesson1.task1;

import utils.BaseHttpServlet;
import utils.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static utils.Constants.FILE_RESOURCE_SEPARATOR;

/**
 * Пробный сервлет
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class HelloWorldServlet extends BaseHttpServlet
{
	private static final String HELLO_WORLD_PAGE = "lesson1" + FILE_RESOURCE_SEPARATOR + "helloWorldPage.html";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Map<String, Object> pageVariables = createPageVariablesMap(request);

		response.getWriter().println(PageGenerator.instance().getPage(HELLO_WORLD_PAGE, pageVariables));
		response.setContentType(HTTP_CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	protected Map<String, Object> createPageVariablesMap(HttpServletRequest request)
	{
		Map<String, Object> pageVariables = new HashMap<>();
		pageVariables.put("URL", request.getRequestURL().toString());
		return pageVariables;
	}
}
