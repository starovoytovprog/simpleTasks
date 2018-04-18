package lesson3.task4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson3.task4.AsciiCharSequence {@link AsciiCharSequence}
 *
 * @author Starovoytov
 * @since 06.04.2018
 */
public class AsciiCharSequenceTest {
    /**
     * Проверка результата выполнения метода
     */
    @Test
    public void testRun() {
        byte[] example = {72, 101, 108, 108, 111, 33};
        CharSequence testSequens = new AsciiCharSequence(example);

        assertEquals(testSequens.length(), 6);
        assertEquals(testSequens.charAt(1), 'e');
        assertEquals(testSequens.toString(), "Hello!");
        assertEquals(testSequens.subSequence(1, 5).toString(), "ello");
    }
}
