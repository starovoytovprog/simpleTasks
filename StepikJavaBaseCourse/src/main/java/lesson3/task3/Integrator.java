package lesson3.task3;

import java.util.function.DoubleUnaryOperator;

/**
 * Реализуйте метод, выполняющий численное интегрирование заданной функции на заданном интервале по формуле левых прямоугольников.
 * Функция задана объектом, реализующим интерфейс java.util.function.DoubleUnaryOperator. Его метод applyAsDouble() принимает значение аргумента и возвращает значение функции в заданной точке.
 * Интервал интегрирования задается его конечными точками a и b, причем a&#8804;b. Для получения достаточно точного результата используйте шаг сетки не больше 10−6.
 * <p>
 * Пример. Вызов integrate(x -&#62; 1, 0, 10) должен возвращать значение 10.
 *
 * @author Starovoytov
 * @since 06.04.2018
 */
public class Integrator {
	public static double integrate(DoubleUnaryOperator f, double a, double b) {
		final int n = 1000000; // количество шагов
		final double step = (b - a)/n; // размер шага
		double result = 0;

		for (int i = 0; i < n; i++) {
			result += f.applyAsDouble(a + step*(i + 0.5));
		}

		result *= step;

		return result;
	}
}
