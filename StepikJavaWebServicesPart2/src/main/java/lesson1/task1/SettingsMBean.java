package lesson1.task1;

/**
 * Интерфейс для бина настроек.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public interface SettingsMBean {

	/**
	 * Получить максимальное количество
	 *
	 * @return максимальное количество
	 */
	int getMaxCount();

	/**
	 * Установить максимальное количество
	 *
	 * @param maxCount максимальное количество
	 */
	void setMaxCount(int maxCount);
}
