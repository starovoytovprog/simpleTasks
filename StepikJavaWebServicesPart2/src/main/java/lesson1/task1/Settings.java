package lesson1.task1;

/**
 * Настройки.
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class Settings implements SettingsMBean {

	private int maxCount = 10;

	@Override
	public int getMaxCount() {
		return maxCount;
	}

	@Override
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
}
