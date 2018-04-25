package utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Базовый класс для сервлетов
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class BaseHttpServlet extends HttpServlet
{
	protected static final String HTTP_CONTENT_TYPE = "text/html;charset=utf-8";

	protected Map<String, Object> createPageVariablesMap(HttpServletRequest request)
	{
		Map<String, Object> pageVariables = new HashMap<>();
		pageVariables.put("URL", request.getRequestURL().toString());
		return pageVariables;
	}
}
