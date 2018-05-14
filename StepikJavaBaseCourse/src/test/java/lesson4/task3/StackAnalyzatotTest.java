package lesson4.task3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson4.task3.StackAnalyzatot {@link StackAnalyzatot}
 *
 * @author Starovoytov
 * @since 09.04.2018
 */
public class StackAnalyzatotTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		Exception ex = new Exception();
		String runMe = ex.getStackTrace()[1].getClassName() + "#" + ex.getStackTrace()[1].getMethodName();

		assertEquals(StackAnalyzatot.getCallerClassAndMethodName(), runMe);
	}
}
