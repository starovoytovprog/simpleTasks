package lesson4.task4;

/**
 * Реализация интерфейса менеджера подключений к роботу, возвращающая коннекшен с ошибкой при закрытии и при перемещении.
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class RobotConnectionErrorInCloseAndMove implements RobotConnection {
    @Override
    public void moveRobotTo(int x, int y) {
        throw new RobotConnectionException("Ошибка при движении");
    }

    @Override
    public void close() {
        throw new RobotConnectionException("Ошибка при закрытии");
    }
}
