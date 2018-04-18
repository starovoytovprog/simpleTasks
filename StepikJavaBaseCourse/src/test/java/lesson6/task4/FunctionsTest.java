package lesson6.task4;

import org.junit.Test;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson6.task4.Functions {@link Functions}
 *
 * @author Starovoytov
 * @since 17.04.2018
 */
public class FunctionsTest {
    /**
     * Проверка результата выполнения метода
     */
    @Test
    public void testRun() {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = Functions.ternaryOperator(condition, ifTrue, ifFalse);

        String testString = new String("Тестовая строка.");
        assertEquals(safeStringLength.apply(null).intValue(), 0);
        assertEquals(safeStringLength.apply(testString).intValue(), testString.length());
    }
}
