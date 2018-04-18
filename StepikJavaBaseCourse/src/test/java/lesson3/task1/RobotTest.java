package lesson3.task1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson3.task1.Robot {@link Robot}
 *
 * @author Starovoytov
 * @since 05.04.2018
 */
public class RobotTest {
    /**
     * Проверка результата выполнения метода
     */
    @Test
    public void testRun() {
        StringBuffer actions = new StringBuffer().append(Robot.TURN_RIGHT).append(Robot.STEP_FORWARD).append(Robot.STEP_FORWARD).append(Robot.STEP_FORWARD);
        assertEquals(actions.toString(), Robot.moveRobot(new Robot(), 3, 0));
    }
}
