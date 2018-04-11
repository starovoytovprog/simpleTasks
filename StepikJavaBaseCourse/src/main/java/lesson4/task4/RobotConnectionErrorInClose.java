package lesson4.task4;

/**
 * Реализация интерфейса для подключения к роботу с ошибкой при закрытии
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class RobotConnectionErrorInClose implements RobotConnection
{
	@Override
	public void moveRobotTo(int x, int y)
	{

	}

	@Override
	public void close()
	{
		throw new RobotConnectionException("Ошибка при закрытии");
	}
}
