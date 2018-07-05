package lesson1.task1;

import Utils.MainServerContainer;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * Запуск сервлета, возвращающего разрешённое количество пользователей
 *
 * @author Starovoytov
 * @since 05.07.2018
 */
public class Main {

	public static final String PATH = "admin";

	public static void main(String[] args) throws Exception {
		UserCountServlet helloWorldServlet = new UserCountServlet(getSettings());
		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		contextHandler.addServlet(new ServletHolder(helloWorldServlet), "/" + PATH);

		MainServerContainer.setHandler(contextHandler);
		MainServerContainer.start();
	}

	/**
	 * Получить настройки, зарегистрированные как м-бин
	 *
	 * @return зарегистрированные настройки
	 * @throws Exception ошибки регистрации
	 */
	private static SettingsMBean getSettings() throws Exception {
		SettingsMBean s = new Settings();
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		ObjectName objectName = new ObjectName("Admin:type=AccountServerController.usersLimit");
		mBeanServer.registerMBean(s, objectName);

		return s;
	}
}
