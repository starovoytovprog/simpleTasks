package lesson4.task4;

/**
 * Интерфейс для подключения к роботу
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public interface RobotConnection extends AutoCloseable {
    /**
     * Даёт команду роботу переместиться в определённую точку.
     *
     * @param x Целевая координата x
     * @param y Целевая координата y
     */
    void moveRobotTo(int x, int y);

    @Override
    void close();
}
