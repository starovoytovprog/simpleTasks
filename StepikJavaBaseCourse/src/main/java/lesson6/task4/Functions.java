package lesson6.task4;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Давайте научимся комбинировать функции в более сложные функции. Для примера построим следующую комбинацию. Дан предикат condition и две функции ifTrue и ifFalse. Напишите метод ternaryOperator, который из них построит новую функцию, возвращающую значение функции ifTrue, если предикат выполнен, и значение ifFalse иначе.
 *
 * @author Starovoytov
 * @since 17.04.2018
 */
public class Functions {
	public static <T, U> Function<T, U> ternaryOperator(Predicate<? super T> condition, Function<? super T, ? extends U> ifTrue, Function<? super T, ? extends U> ifFalse) {

		return t -> condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
	}
}
