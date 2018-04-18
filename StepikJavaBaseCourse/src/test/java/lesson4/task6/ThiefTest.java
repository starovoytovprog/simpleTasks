package lesson4.task6;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson4.task6.Thief {@link Thief}
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class ThiefTest {
    /**
     * Проверка результата выполнения метода
     */
    @Test
    public void testRun() {
        MailService thief = new Thief(10);

        Sendable mailMessage = new MailMessage("from", "to", "content");
        assertTrue(mailMessage.equals(thief.processMail(mailMessage)));

        Sendable cheapPackage = new MailPackage("from", "to", new Package("cheap package", 9));
        assertTrue(cheapPackage.equals(thief.processMail(cheapPackage)));

        Sendable expensivePackage = new MailPackage("from", "to", new Package("expensive package", 10));
        assertFalse(expensivePackage.equals(thief.processMail(expensivePackage)));
    }
}
