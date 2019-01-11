package mainpack;

/**
 * HelloWorld
 *
 * @author Starovoytov
 * @since 29.11.2017
 */
public class HelloWorldMain {
	/**
	 * Вывод "Hello World!" в консоль
	 *
	 * @param args Не используется
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");
		String version = HelloWorldMain.class.getPackage().getImplementationVersion();
		if (version != null) {
			System.out.println("Build info: " + version);
		}
	}
}
