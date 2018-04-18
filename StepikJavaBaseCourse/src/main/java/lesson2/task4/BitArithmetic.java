package lesson2.task4;

/**
 * Реализуйте метод flipBit, изменяющий значение одного бита заданного целого числа на противоположное. Данная задача актуальна, например, при работе с битовыми полями.
 * Договоримся, что биты нумеруются от младшего (индекс 1) к старшему (индекс 32).
 * Воспользуйтесь предоставленным шаблоном. Декларацию класса, метод main и обработку ввода-вывода добавит проверяющая система.
 * <p>
 * Sample Input: 0 1; Sample Output: 1
 *
 * @author Starovoytov
 * @since 03.04.2018
 */
public class BitArithmetic {
    /**
     * Flips one bit of the given <code>value</code>.
     *
     * @param value    any number
     * @param bitIndex index of the bit to flip, 1 &#8804; bitIndex &#8804; 32
     * @return new value with one bit flipped
     */
    public static int flipBit(int value, int bitIndex) {
        return value ^ 1 << --bitIndex;
    }
}
