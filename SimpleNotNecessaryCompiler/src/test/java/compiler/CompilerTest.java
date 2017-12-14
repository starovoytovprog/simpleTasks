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

	/**
	 * Тестирование компиляции простого дерева печати выражения +.
	 */
	@Test
	public void simplePrintSumTest()
	{
		Node expectedNode = new Node();
		expectedNode.setType(NodeType.PRINT);
		Node sum1Node = new Node();
		sum1Node.setType(NodeType.SUM);
		Node digit1Node = new Node();
		digit1Node.setType(NodeType.DIGIT);
		digit1Node.setValue("1");
		Node sum2Node = new Node();
		sum2Node.setType(NodeType.SUM);
		Node digit2Node = new Node();
		digit2Node.setType(NodeType.DIGIT);
		digit2Node.setValue("2");
		Node digit3Node = new Node();
		digit3Node.setType(NodeType.DIGIT);
		digit3Node.setValue("3");
		sum2Node.addDependentNode(digit1Node);
		sum2Node.addDependentNode(digit2Node);
		sum1Node.addDependentNode(sum2Node);
		sum1Node.addDependentNode(digit3Node);
		expectedNode.addDependentNode(sum1Node);
		Node nextNode = new Node();
		nextNode.setType(NodeType.EOF);
		expectedNode.addDependentNode(nextNode);

		String expectedMachineCode = "PUSH 1" + COMMAND_LINE_DELIMITER +
			"PUSH 2" + COMMAND_LINE_DELIMITER +
			"ADD" + COMMAND_LINE_DELIMITER +
			"PUSH 3" + COMMAND_LINE_DELIMITER +
			"ADD" + COMMAND_LINE_DELIMITER +
			"ECHO" + COMMAND_LINE_DELIMITER +
			"HALT";
		String compilerString = compiler.compile(expectedNode);

		assertEquals(expectedMachineCode, compilerString);
	}

	/**
	 * Тестирование компиляции простого дерева задания переменной.
	 */
	@Test
	public void simpleVariableTest()
	{
		Node expectedNode = new Node();
		expectedNode.setType(NodeType.VARIABLE);
		expectedNode.setValue("x");
		Node setNode = new Node();
		setNode.setType(NodeType.SET);
		expectedNode.addDependentNode(setNode);
		Node valueNode = new Node();
		valueNode.setType(NodeType.DIGIT);
		valueNode.setValue("5");
		expectedNode.addDependentNode(valueNode);
		Node nextNode = new Node();
		nextNode.setType(NodeType.EOF);
		expectedNode.addDependentNode(nextNode);

		String expectedMachineCode = "PUSH 5" + COMMAND_LINE_DELIMITER +
			"STORE x" + COMMAND_LINE_DELIMITER +
			"HALT";
		String compilerString = compiler.compile(expectedNode);

		assertEquals(expectedMachineCode, compilerString);
	}
}
