package lesson4.task6;

/**
 * Класс, в котором скрыта логика настоящей почты.
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class RealMailService implements MailService {
    @Override
    public Sendable processMail(Sendable mail) {
        // Здесь описан код настоящей системы отправки почты.
        return mail;
    }
}
