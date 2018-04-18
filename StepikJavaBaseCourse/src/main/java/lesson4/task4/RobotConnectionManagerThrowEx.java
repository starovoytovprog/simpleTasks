package lesson4.task4;

/**
 * Реализация интерфейса менеджера подключений к роботу, кидающая исключения
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class RobotConnectionManagerThrowEx implements RobotConnectionManager {
    @Override
    public RobotConnection getConnection() throws RobotConnectionException {
        throw new RobotConnectionException("Тестируем исключения");
    }
}
