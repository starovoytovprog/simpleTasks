package lesson6.task1;

/**
 * Реализуйте generic-класс Pair, похожий на Optional, но содержащий пару элементов разных типов и не запрещающий элементам принимать значение null.
 * Реализуйте методы getFirst(), getSecond(), equals() и hashCode(), а также статический фабричный метод of(). Конструктор должен быть закрытым (private).
 * Пожалуйста, не меняйте модификатор доступа класса Pair. Для корректной проверки класс должен иметь пакетную видимость.
 *
 * @author Starovoytov
 * @since 16.04.2018
 */
public class Pair<T, U> {
    T first;
    U second;

    private Pair() {
    }

    @SuppressWarnings("unchecked")
    public static <X, Y> Pair of(X first, Y second) {
        Pair p = new Pair();
        p.first = first;
        p.second = second;

        return p;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Pair) {
            Pair o2 = (Pair) o;

            boolean f = this.getFirst() == null && o2.getFirst() == null;
            boolean s = this.getSecond() == null && o2.getSecond() == null;

            if (this.getFirst() != null && o2.getSecond() != null) {
                f = this.getFirst().equals(o2.getFirst());
            }

            if (this.getSecond() != null && o2.getSecond() != null) {
                s = this.getSecond().equals(o2.getSecond());
            }

            return f && s;
        }

        return false;
    }

    @Override
    public int hashCode() {
        final int k = 235624376;
        int hash = 782364589;
        hash = (getFirst().hashCode() | (1 >> 16)) * k + hash;
        hash = (getSecond().hashCode() | (1 >> 32)) * k + hash;
        return hash;
    }
}
