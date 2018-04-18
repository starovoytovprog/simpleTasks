package lesson2.task5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson2.task5.Cast {@link Cast}
 *
 * @author Starovoytov
 * @since 03.04.2018
 */
public class CastTest {
    /**
     * Проверка результата выполнения метода
     */
    @Test
    public void testRun() {
        assertEquals(Cast.charExpression(32), '|');
        assertEquals(Cast.charExpression(29), 'y');
    }
}
