package lesson4.task4;

/**
 * Реализация интерфейса менеджера подключений к роботу, возвращающая коннекшен с ошибкой при закрытии и движении.
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class RobotConnectionManagerImpl2 implements RobotConnectionManager {
	@Override
	public RobotConnection getConnection() throws RobotConnectionException {
		return new RobotConnectionErrorInCloseAndMove();
	}
}
