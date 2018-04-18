package lesson2.task7;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson2.task7.IsPalindrome {@link IsPalindrome}
 *
 * @author Starovoytov
 * @since 04.04.2018
 */
public class IsPalindromeTest {
    /**
     * Проверка результата выполнения метода
     */
    @Test
    public void testRun() {
        assertTrue(IsPalindrome.isPalindrome("Madam, I'm Adam!"));
    }
}
