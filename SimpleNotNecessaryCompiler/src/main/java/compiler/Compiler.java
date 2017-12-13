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
	/**
	 * Формирование машинного кода
	 *
	 * @param rootNode корень синтаксического дерева
	 * @return машинный код
	 */
	public String compile(Node rootNode)
	{
		return nodeToString(rootNode);
	}

	private String nodeToString(Node nextNode)
	{
		String result = "";

		switch (nextNode.getType())
		{
			case PRINT:
			{
				result += printNodeToString(nextNode);
				break;
			}
			case DIGIT:
			{
				result += digitNodeToString(nextNode);
				break;
			}
			case EOF:
			{
				result += eofNodeToString(nextNode);
				break;
			}
		}

		return result;
	}

	private String printNodeToString(Node node)
	{
		String printNodeString = nodeToString(node.getDependentNode(0));
		printNodeString += COMMAND_LINE_DELIMITER;
		printNodeString += "ECHO";
		printNodeString += COMMAND_LINE_DELIMITER;
		printNodeString += nodeToString(node.getDependentNode(1));

		return printNodeString;
	}

	private String digitNodeToString(Node node)
	{
		return "PUSH " + node.getValue();
	}

	private String eofNodeToString(Node node)
	{
		return "HALT";
	}
}
