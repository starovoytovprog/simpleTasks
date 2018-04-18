package lesson2.task6;

/**
 * Реализуйте метод, проверяющий, является ли заданное число по абсолютной величине степенью двойки.
 * Решать можно разными способами:
 * 1) воспользовавшись одним удобным статическим методом из класса java.lang.Integer;
 * 2) применив пару трюков из двоичной арифметики;
 * 3) написав решение "в лоб" с циклом и условными операторами (можете вернуться к этой задаче после просмотра соответствующих уроков).
 * <p>
 * Sample Input 1: 0; Sample Output 1: false
 * Sample Input 2: 1; Sample Output 2: true
 * Sample Input 3: -2; Sample Output 3: true
 *
 * @author Starovoytov
 * @since 03.04.2018
 */
public class IsPowerOfTwo {
    /**
     * Checks if given <code>value</code> is a power of two.
     *
     * @param value any number
     * @return <code>true</code> when <code>value</code> is power of two, <code>false</code> otherwise
     */
    public static boolean isPowerOfTwo(int value) {
        return Integer.bitCount(Math.abs(value)) == 1;
    }
}
