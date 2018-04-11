package lesson4.task1;

import java.math.BigDecimal;

/**
 * Допустим, в программе используется следующее приведение типа:
 * A a = (A) b;
 * где A — имя какого-то класса, a и b — переменные.
 * Если компилятор сможет сразу определить, что такое приведение невозможно, то компиляция завершится ошибкой. Однако не всегда компилятор может это сделать, т.к. фактический объект, находящийся по ссылке b, на момент компиляции не известен. Поэтому иногда такой код может успешно скомпилироваться, но упасть с ошибкой во время исполнения.
 * Какое исключение выбросит JVM, если во время выполнения программы окажется, что ссылка b не может быть приведена к типу A?
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class CastError
{
	public static void getCastError()
	{
		BigDecimal bd = new BigDecimal(3.14);
		Integer i = (Integer) (Object) bd;
	}
}
