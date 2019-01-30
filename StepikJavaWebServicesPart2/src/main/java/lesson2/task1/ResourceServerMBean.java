package lesson2.task1;

/**
 * Интерфейс для бина сервера ресурсов.
 *
 * @author Starovoytov
 * @since 29.01.2019
 */
public interface ResourceServerMBean {
	String getName();

	int getAge();

	void loadData(String path);
}
