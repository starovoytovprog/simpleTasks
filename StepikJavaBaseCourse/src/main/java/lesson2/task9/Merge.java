package lesson2.task9;

/**
 * Реализуйте метод, сливающий два отсортированных по неубыванию массива чисел в один отсортированный в том же порядке массив. Массивы могут быть любой длины, в том числе нулевой.
 * Предполагается, что вы реализуете алгоритм слияния, имеющий линейную сложность: он будет идти по двум исходным массивам и сразу формировать отсортированный результирующий массив. Так, чтобы сортировка полученного массива при помощи Arrays.sort() уже не требовалась. К сожалению, автоматически это не проверить, так что это остается на вашей совести :)
 * <p>
 * Если на вход подаются массивы {0, 2, 2} и {1, 3}, то на выходе должен получиться массив {0, 1, 2, 2, 3}
 *
 * @author Starovoytov
 * @since 04.04.2018
 */
public class Merge {
    /**
     * Merges two given sorted arrays into one
     *
     * @param a1 first sorted array
     * @param a2 second sorted array
     * @return new array containing all elements from a1 and a2, sorted
     */
    public static int[] mergeArrays(int[] a1, int[] a2) {
        int[] a3 = new int[a1.length + a2.length];

        for (int i = 0, j = 0; i < a1.length || j < a2.length; ) {
            if (i >= a1.length) {
                a3[i + j] = a2[j];
                j++;
                continue;
            }

            if (j >= a2.length || a1[i] < a2[j]) {
                a3[i + j] = a1[i];
                i++;
            } else {
                a3[i + j] = a2[j];
                j++;
            }
        }

        return a3;
    }
}
