package lesson6.task6;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson6.task6.Random {@link StreamHelper}
 *
 * @author Starovoytov
 * @since 18.04.2018
 */
public class StreamHelperTest {
    /**
     * Проверка результата выполнения метода
     */
    @Test
    public void testRun() {
        Stream<Integer> is = Stream.of(1, 2, -3, 10, 5, 6, 7, 0, 9);
        StreamHelper.findMinMax(is, (i1, i2) ->
                {
                    if (i1 == i2) {
                        return 0;
                    }

                    if (i1 > i2) {
                        return 1;
                    }

                    return -1;
                }
                , (x, y) ->
                {
                    assertEquals(x.intValue(), -3);
                    assertEquals(y.intValue(), 10);
                });
    }
}
