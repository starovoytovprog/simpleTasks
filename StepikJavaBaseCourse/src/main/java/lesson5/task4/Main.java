package lesson5.task4;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Напишите программу, читающую текст из System.in и выводящую в System.out сумму всех встреченных в тексте вещественных чисел с точностью до шестого знака после запятой. Числом считается последовательность символов, отделенная от окружающего текста пробелами или переводами строк и успешно разбираемая методом Double.parseDouble.
 * На этот раз вам надо написать программу полностью, т.е. объявить класс (с именем Main — таково ограничение проверяющей системы), метод main, прописать все import'ы.
 * Sample Input: 1 2 3; Sample Output: 6.000000
 * Sample Input: a1 b2 c3; Sample Output: 0.000000
 * Sample Input: -1e3 18 .111 11bbb; Sample Output: -981.889000
 *
 * @author Starovoytov
 * @since 12.04.2018
 */
public class Main
{
	public static void main(String[] args) throws IOException
	{
		double result = 0;
		Scanner s = new Scanner(System.in);
		s.useLocale(Locale.ENGLISH);
		s.useDelimiter(" |" + System.lineSeparator() + "");

		while (s.hasNext())
		{
			try
			{
				result += Double.parseDouble(s.next());
			}
			catch (Exception ex)
			{
			}
		}

		System.out.format(Locale.ENGLISH, "%.6f", result);
	}
}
