package lesson6.task3;

import java.util.*;

/**
 * Напишите программу, которая прочитает из System.in последовательность целых чисел, разделенных пробелами, затем удалит из них все числа, стоящие на четных позициях, и затем выведет получившуюся последовательность в обратном порядке в System.out.
 * Все числа влезают в int. Позиции чисел в последовательности нумеруются с нуля.
 * В этом задании надо написать программу целиком, включая import'ы, декларацию класса Main и метода main.
 * Sample Input: 1 2 3 4 5 6 7; Sample Output: 6 4 2
 *
 * @author Starovoytov
 * @since 16.04.2018
 */
public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		s.useLocale(Locale.ENGLISH);
		s.useDelimiter(" |" + System.lineSeparator());
		Queue<Integer> list = new ArrayDeque<>();
		boolean isAdd = false;

		while (s.hasNext()) {
			try {
				if (isAdd) {
					list.add(Integer.parseInt(s.next()));
				}
				else {
					s.next();
				}

				isAdd = !isAdd;
			}
			catch (Exception ex) {
			}
		}

		Iterator iterator = ((ArrayDeque) list).descendingIterator();

		while (iterator.hasNext()) {
			System.out.print(iterator.next());

			if (iterator.hasNext()) {
				System.out.print(" ");
			}
		}
	}
}
