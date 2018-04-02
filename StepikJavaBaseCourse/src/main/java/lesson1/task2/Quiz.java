package lesson1.task2;

import java.security.MessageDigest;

/**
 * Чтобы убедиться, что вы действительно научились пользоваться компилятором и запускать Java-программы, запустите следующую программу.
 * В качестве ответа введите то, что эта программа напечатает в консоль.
 *
 * @author Starovoytov
 * @since 02.04.2018
 */
public class Quiz
{
	public static void main(String[] args) throws Exception
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest("abracadabra".getBytes("UTF-8"));
		for (byte b : digest)
		{
			System.out.printf("%02x", b);
		}
	}
}
