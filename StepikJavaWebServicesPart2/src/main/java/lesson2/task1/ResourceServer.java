package lesson2.task1;

/**
 * Ресурсный сервер
 *
 * @author Starovoytov
 * @since 29.01.2019
 */
public class ResourceServer implements ResourceServerMBean {
	private TestResource testResource = new TestResource();

	@Override
	public String getName() {
		return testResource.getName();
	}

	@Override
	public int getAge() {
		return testResource.getAge();
	}

	@Override
	public void loadData(String path) {
		
	}
}
