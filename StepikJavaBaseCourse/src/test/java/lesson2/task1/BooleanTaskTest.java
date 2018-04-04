package lesson2.task1;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson2.task1.BooleanTask {@link BooleanTask}
 *
 * @author Starovoytov
 * @since 02.04.2018
 */
public class BooleanTaskTest
{
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun()
	{
		assertFalse(BooleanTask.booleanExpression(false, false, false, false));
		assertFalse(BooleanTask.booleanExpression(true, true, true, true));
		assertTrue(BooleanTask.booleanExpression(false, false, true, true));
	}
}
