package lesson1.task1;

import java.io.IOException;
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
	private final SettingsMBean settingsMBean;

	/**
	 * Конструктор
	 *
	 * @param setting Настройки в виде м-бина
	 */
	public UserCountServlet(SettingsMBean setting) {
		settingsMBean = setting;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().println(settingsMBean.getMaxCount());
		response.setContentType("http");
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
