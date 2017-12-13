package compiler;

import nodes.Node;

import static vm.Constants.COMMAND_LINE_DELIMITER;

/**
 * Компилятор
 * Строит машинный код из синтаксического дерева
 *
 * @author Starovoytov
 * @since 13.12.2017
 */
public class Compiler
{
	private String mashineCodeString;

	/**
	 * Формирование машинного кода
	 *
	 * @param rootNode корень синтаксического дерева
	 * @return машинный код
	 */
	public String compile(Node rootNode)
	{
		mashineCodeString = "";
		nodeToString(rootNode);
		return mashineCodeString;
	}

	private void nodeToString(Node nextNode)
	{
		switch (nextNode.getType())
		{
			case PRINT:
			{
				printNodeToString(nextNode);
				break;
			}
			case DIGIT:
			{
				digitNodeToString(nextNode);
				break;
			}
			case EOF:
			{
				eofNodeToString(nextNode);
				break;
			}
			case SUM:
			{
				sumNodeToString(nextNode);
				break;
			}
			case MINUS:
			{
				minusNodeToString(nextNode);
				break;
			}
		}
	}

	private void printNodeToString(Node node)
	{
		nodeToString(node.getDependentNode(0));
		mashineCodeString += "ECHO";
		mashineCodeString += COMMAND_LINE_DELIMITER;
		nodeToString(node.getDependentNode(1));
	}

	private void digitNodeToString(Node node)
	{
		mashineCodeString += "PUSH " + node.getValue() + COMMAND_LINE_DELIMITER;
	}

	private void eofNodeToString(Node node)
	{
		mashineCodeString += "HALT";
	}

	private void sumNodeToString(Node node)
	{
		nodeToString(node.getDependentNode(0));
		nodeToString(node.getDependentNode(1));
		mashineCodeString += "ADD";
		mashineCodeString += COMMAND_LINE_DELIMITER;
	}

	private void minusNodeToString(Node node)
	{
		nodeToString(node.getDependentNode(1));
		nodeToString(node.getDependentNode(0));
		mashineCodeString += "SUB";
		mashineCodeString += COMMAND_LINE_DELIMITER;
	}
}
