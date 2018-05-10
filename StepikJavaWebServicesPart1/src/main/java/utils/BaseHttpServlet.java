package utils;

import javax.servlet.http.HttpServlet;

/**
 * Базовый класс для сервлетов
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class BaseHttpServlet extends HttpServlet
{
	public static final String FILE_RESOURCE_SEPARATOR = "/";
	protected static final String HTTP_CONTENT_TYPE = "text/html;charset=utf-8";
	protected static final String JSON_CONTENT_TYPE = "application/json;charset=utf-8";
}
