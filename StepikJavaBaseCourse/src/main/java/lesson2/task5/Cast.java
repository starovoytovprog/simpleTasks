package lesson2.task5;

/**
 * Реализуйте метод, который возвращает букву, стоящую в таблице UNICODE после символа "\" (обратный слэш) на расстоянии a.
 * <p>
 * Sample Input 1: 32; Sample Output 1: |
 * Sample Input 2: 29; Sample Output 2: y
 *
 * @author Starovoytov
 * @since 03.04.2018
 */
public class Cast {
    private static final char START_CHAR = '\\';

    public static char charExpression(int a) {
        return (char) (START_CHAR + a);
    }
}
