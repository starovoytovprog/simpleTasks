package lesson4.task6;

import org.junit.Test;

import static lesson4.task6.Constants.BANNED_SUBSTANCE;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson4.task6.Inspector {@link Inspector}
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class InspectorTest {
    /**
     * Проверка результата выполнения метода
     */
    @Test
    public void testRun() {
        MailService inspector = new Inspector();
        boolean isException = false;

        try {
            inspector.processMail(new MailMessage("from", "to", BANNED_SUBSTANCE));
        } catch (Throwable t) {
            assertTrue(false);
        }

        try {
            inspector.processMail(new MailPackage("from", "to", new Package("simple pack", 100)));
        } catch (Throwable t) {
            assertTrue(false);
        }

        try {
            inspector.processMail(new MailPackage("from", "to", new Package("stones", 100)));
        } catch (Throwable t) {
            assertTrue(t instanceof StolenPackageException);
            isException = true;
        }

        assertTrue(isException);
        isException = false;

        try {
            inspector.processMail(new MailPackage("from", "to", new Package(BANNED_SUBSTANCE, 100)));
        } catch (Throwable t) {
            assertTrue(t instanceof IllegalPackageException);
            isException = true;
        }
        assertTrue(isException);
    }
}
