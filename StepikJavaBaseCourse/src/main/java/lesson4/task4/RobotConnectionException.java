package lesson4.task4;

/**
 * Ошибка подключения к роботу
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class RobotConnectionException extends RuntimeException {
    public RobotConnectionException(String message) {
        super(message);
    }

    public RobotConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
