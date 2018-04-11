package lesson4.task1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson4.task1.CastError {@link CastError}
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class CastErrorTest
{
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun()
	{
		String classCastExceptionName = "java.lang.ClassCastException";

		try
		{
			CastError.getCastError();
		}
		catch (ClassCastException ex)
		{
			assertEquals(ex.getClass().getCanonicalName(), classCastExceptionName);
		}
	}
}
