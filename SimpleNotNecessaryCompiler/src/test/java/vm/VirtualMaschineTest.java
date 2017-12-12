package vm;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static vm.Constants.COMMAND_LINE_DELIMITER;

/**
 * Тестирование HelloWorld {@link VirtualMaschine}
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class VirtualMaschineTest
{
	private static final VirtualMaschine virtualMaschine = new VirtualMaschine();

	/**
	 * Тестируется простой вывод числа
	 *
	 * @throws Exception ошибка в машинном коде
	 */
	@Test
	public void simpleOut() throws Exception
	{
		String machineCode = "ECHO 10";
		String expectedResult = "10";

		testExecute(machineCode, expectedResult);
	}

	/**
	 * Тестируется простой вывод значения из стека
	 *
	 * @throws Exception ошибка в машинном коде
	 */
	@Test
	public void simpleStackValueOut() throws Exception
	{
		String machineCode = "PUSH 15" + COMMAND_LINE_DELIMITER +
			"ECHO";
		String expectedResult = "15";

		testExecute(machineCode, expectedResult);
	}

	/**
	 * Тестируется сложение двух чисел в стэке
	 *
	 * @throws Exception ошибка в машинном коде
	 */
	@Test
	public void simpleStackAdd() throws Exception
	{
		String machineCode = "PUSH 2" + COMMAND_LINE_DELIMITER +
			"PUSH 7" + COMMAND_LINE_DELIMITER +
			"ADD" + COMMAND_LINE_DELIMITER +
			"ECHO";
		String expectedResult = "9";

		testExecute(machineCode, expectedResult);
	}

	private void testExecute(String mashineCode, String expectedResult) throws Exception
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		virtualMaschine.run(mashineCode);
		System.out.flush();
		System.setOut(old);
		assertTrue(baos.toString().equals(expectedResult + "\r\n"));
	}
}
