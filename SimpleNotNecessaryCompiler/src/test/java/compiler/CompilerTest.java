package compiler;

import nodes.Node;
import nodes.NodeType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static vm.Constants.COMMAND_LINE_DELIMITER;

/**
 * Тестирование компилятора {@link Compiler}
 *
 * @author Starovoytov
 * @since 13.12.2017
 */
public class CompilerTest
{
	private static final Compiler compiler = new Compiler();

	/**
	 * Тестирование компиляции простого дерева печати.
	 */
	@Test
	public void simplePrintTest()
	{
		Node expectedNode = new Node();
		expectedNode.setType(NodeType.PRINT);
		Node printValueNode = new Node();
		printValueNode.setType(NodeType.DIGIT);
		printValueNode.setValue("1");
		expectedNode.addDependentNode(printValueNode);
		Node nextNode = new Node();
		nextNode.setType(NodeType.EOF);
		expectedNode.addDependentNode(nextNode);

		String expectedMachineCode = "PUSH 1" + COMMAND_LINE_DELIMITER +
			"ECHO" + COMMAND_LINE_DELIMITER +
			"HALT";
		String compilerString = compiler.compile(expectedNode);

		assertEquals(expectedMachineCode, compilerString);
	}
}
