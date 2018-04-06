package lesson3.task3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson3.task3.Integrator {@link Integrator}
 *
 * @author Starovoytov
 * @since 06.04.2018
 */
public class IntegratorTest
{
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun()
	{
		assertEquals(Integrator.integrate(x -> 1, 0, 10), 10, 0.0001);
		assertEquals(Integrator.integrate(x -> x + 2, 0, 10), 70, 0.0001);
	}
}
