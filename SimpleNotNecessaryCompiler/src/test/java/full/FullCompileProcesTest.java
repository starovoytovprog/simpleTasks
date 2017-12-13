package full;

import compiler.Compiler;
import nodes.Node;
import nodes.Parser;
import org.junit.Test;
import tokens.LexicalAnalysis;
import tokens.TokenList;
import vm.VirtualMaschine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование полного процесса от исходного кода до выполнения
 *
 * @author Starovoytov
 * @since 13.12.2017
 */
public class FullCompileProcesTest
{
	private static final LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();
	private static final Parser parser = new Parser();
	private static final Compiler compiler = new Compiler();
	private static final VirtualMaschine virtualMaschine = new VirtualMaschine();

	/**
	 * Компиляция и исполнение команды печати.
	 *
	 * @throws Exception ошибка выполнения
	 */
	@Test
	public void simplePrintCommandTest() throws Exception
	{
		testExecute("print(14);", "14");
	}

	/**
	 * Компиляция и исполнение команды печати выражения.
	 *
	 * @throws Exception ошибка выполнения
	 */
	@Test
	public void simplePrintExpressionCommandTest() throws Exception
	{
		testExecute("print(5-(1+6-1+7-1)+8-1+9);", "9");
	}

	/**
	 * Проверка работы многострочного кода с пробелами
	 *
	 * @throws Exception ошибка выполнения
	 */
	@Test
	public void simpleTwoStringTest() throws Exception
	{
		testExecute("   print   (   1   +   1    - 2    )    ;     \n" +
			"print(2);" +
			"", "0\r\n2");
	}

	private void testExecute(String sourceCode, String expectedResult) throws Exception
	{
		TokenList list = lexicalAnalysis.analys(sourceCode);
		Node rootNode = parser.parse(list);
		String mashineCode = compiler.compile(rootNode);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);

		virtualMaschine.run(mashineCode);

		System.out.flush();
		System.setOut(old);

		assertEquals(baos.toString(), expectedResult + (expectedResult.isEmpty() ? "" : "\r\n"));
	}
}
