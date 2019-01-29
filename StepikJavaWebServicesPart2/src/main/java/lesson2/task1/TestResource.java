package lesson2.task1;

/**
 * Тестовый ресурс
 *
 * @author Starovoytov
 * @since 29.01.2019
 */
public class TestResource {
	private String name;
	private int age;

	public TestResource(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public TestResource() {
		this.name = "";
		this.age = 0;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}
