package lesson3.task2;

/**
 * Дан класс ComplexNumber. Переопределите в нем методы equals() и hashCode() так, чтобы equals() сравнивал экземпляры ComplexNumber по содержимому полей re и im, а hashCode() был бы согласованным с реализацией equals().
 * Реализация hashCode(), возвращающая константу или не учитывающая дробную часть re и im, засчитана не будет
 * <p>
 * Подсказка 1. Поищите в классе java.lang.Double статический метод, который поможет в решении задачи.
 * Подсказка 2. Если задача никак не решается, можно позвать на помощь среду разработки, которая умеет сама генерировать equals() и hashCode(). Если вы воспользовались помощью IDE, то разберитесь, что было сгенерировано и почему оно выглядит именно так. Когда вас на собеседовании попросят на бумажке реализовать equals() и hashCode() для какого-нибудь простого класса, то среда разработки помочь не сможет.
 *
 * @author Starovoytov
 * @since 05.04.2018
 */
public final class ComplexNumber {
	private final double re;
	private final double im;

	public ComplexNumber(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public double getRe() {
		return re;
	}

	public double getIm() {
		return im;
	}

	@Override
	public boolean equals(Object n2) {
		if (n2 == null) {
			return false;
		}

		if (this == n2) return true;

		if (n2 instanceof ComplexNumber) {
			ComplexNumber n3 = (ComplexNumber) n2;
			return this.re == n3.re && this.im == n3.im;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int h1 = (Double.hashCode(re) | 32)>>16;
		int h2 = (Double.hashCode(im) | 4)>>>10;
		return Integer.MAX_VALUE*h1 + h2;
	}
}
