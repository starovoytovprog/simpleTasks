package lesson1.task1;

import utils.BaseHttpServlet;
import utils.PageGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Пробный сервлет
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class HelloWorldServlet extends BaseHttpServlet
{
	private static final String HELLO_WORLD_PAGE = "lesson1" + File.separator + "helloWorldPage.html";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Map<String, Object> pageVariables = createPageVariablesMap(request);

		response.getWriter().println(PageGenerator.instance().getPage(HELLO_WORLD_PAGE, pageVariables));
		response.setContentType(HTTP_CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
	}
}
