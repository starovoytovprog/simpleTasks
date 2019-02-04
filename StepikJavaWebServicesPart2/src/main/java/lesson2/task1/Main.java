package lesson2.task1;

import Utils.MainServerContainer;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * Запуск сервлета ресурсной системы
 *
 * @author Starovoytov
 * @since 29.01.2019
 */
public class Main {
	public static final String PATH = "resources";

	public static void main(String[] args) throws Exception {

		ResourceServerMBean resourceServer = new ResourceServer();
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		ObjectName objectName = new ObjectName("Admin:type=ResourceServerController");
		mBeanServer.registerMBean(resourceServer, objectName);

		ResourceServlet servlet = new ResourceServlet(resourceServer);
		ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		contextHandler.addServlet(new ServletHolder(servlet), "/" + PATH);

		MainServerContainer.setHandler(contextHandler);
		MainServerContainer.start();
	}
}
