package mainpack;

import rand.Randomizer;

import java.io.IOException;

/**
 * Стартовый класс утилиты
 *
 * @author Starovoytov
 * @since 24.09.2018
 */
public class MainClass {

	/**
	 * Запуск утилиты
	 *
	 * @param args Не используется
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Randomizer started!");
		if (args.length < 2) {
			System.out.println("First parameter - source path, second - destination path.");
			return;
		}

		Randomizer.CopyAll(args[0], args[1]);
	}
}
