package lesson4.task6;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson4.task6.UntrustworthyMailWorker {@link UntrustworthyMailWorker}
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class UntrustworthyMailWorkerTest {
    /**
     * Проверка результата выполнения метода
     */
    @Test
    public void testRun() {
        MailService[] services = new MailService[2];
        services[0] = new Thief(100);
        services[1] = new Inspector();
        MailService untrustworthyMailWorkerTest = new UntrustworthyMailWorker(services);

        try {
            untrustworthyMailWorkerTest.processMail(new MailPackage("from", "to", new Package("pack", 150)));
        } catch (Throwable t) {
            assertTrue(t instanceof StolenPackageException);
            return;
        }

        assertTrue(false);
    }
}
