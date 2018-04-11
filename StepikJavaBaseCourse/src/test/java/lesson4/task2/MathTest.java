package lesson4.task2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson4.task2.Math {@link Math}
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class MathTest
{
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun()
	{
		String IllegalArgumentExceptionName = "java.lang.IllegalArgumentException";
		double testValue = -5;
		double result = 1;

		try
		{
			result = (int) Math.sqrt(testValue);
		}
		catch (Exception ex)
		{
			assertEquals(ex.getClass().getCanonicalName(), IllegalArgumentExceptionName);
			assertEquals(ex.getMessage(), Math.ERROR_MESSAGE + testValue);
		}

		assertEquals(result, 1.0, 0.00001);
	}
}
