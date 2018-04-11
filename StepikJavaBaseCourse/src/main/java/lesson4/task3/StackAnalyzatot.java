package lesson4.task3;

/**
 * Реализуйте метод, позволяющий другим методам узнать, откуда их вызвали.
 * Метод getCallerClassAndMethodName() должен возвращать имя класса и метода, откуда вызван метод, вызвавший данный утилитный метод. Или null (нулевую ссылку, а не строку "null"), если метод, вызвавший getCallerClassAndMethodName() является точкой входа в программу, т.е. его никто не вызывал.
 * Это актуально, например, в библиотеках логирования, где для каждого сообщения в логе надо выводить класс и метод, откуда сообщение было залогировано.
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class StackAnalyzatot
{
	public static String getCallerClassAndMethodName()
	{
		Throwable t = new Exception();

		if (t.getStackTrace().length < 3)
		{
			return null;
		}

		StackTraceElement element = t.getStackTrace()[2];

		if (element != null)
		{
			StringBuilder sb = new StringBuilder();
			sb.append(element.getClassName());
			sb.append("#");
			sb.append(element.getMethodName());
			return sb.toString();
		}

		return null;
	}
}
