package lesson4.task4;

/**
 * Интерфейс менеджера подключений к роботу
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public interface RobotConnectionManager {
    /**
     * Попытка соединиться с роботом
     *
     * @return установленное соединение
     */
    RobotConnection getConnection() throws RobotConnectionException;
}
