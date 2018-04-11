package lesson4.task4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson4.task4.RobotCommander {@link RobotCommander}
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class RobotCommanderTest
{
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun()
	{
		testErrorManager(new RobotConnectionManagerThrowEx());
		testErrorManager(new RobotConnectionManagerImpl1());
		testErrorManager(new RobotConnectionManagerImpl2());
	}

	private void testErrorManager(RobotConnectionManager errorManager)
	{
		try
		{
			RobotCommander.moveRobot(errorManager, 10, 10);
		}
		catch (Exception ex)
		{
			assertEquals(RobotCommander.getCount(), 3);
			assertEquals(RobotCommander.getCatch_count(), 3);
		}
	}
}
