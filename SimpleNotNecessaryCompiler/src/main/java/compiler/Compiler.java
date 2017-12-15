package compiler;

import nodes.Node;
import nodes.NodeType;

import java.util.HashMap;
import java.util.Map;

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
	private static final String BLOCK_INSERT_DELIMITER = "---BLOCK_";

	private String mashineCodeString;
	private int blockCount;
	private Map<Integer, String> Blocks;

	/**
	 * Формирование машинного кода
	 *
	 * @param rootNode корень синтаксического дерева
	 * @return машинный код
	 */
	public String compile(Node rootNode)
	{
		mashineCodeString = "";
		blockCount = 0;
		Blocks = new HashMap<>();
		nodeToString(rootNode);
		blocksToCode();
		return mashineCodeString;
	}

	private void blocksToCode()
	{
		for (Integer id : Blocks.keySet())
		{
			mashineCodeString = mashineCodeString.replaceAll(BLOCK_INSERT_DELIMITER + id, nextCodeLineNumber() + "");
			mashineCodeString += COMMAND_LINE_DELIMITER;
			mashineCodeString += Blocks.get(id);
		}
	}

	//TODO: взять команды из VmCommands
	private void nodeToString(Node nextNode)
	{
		if (nextNode == null)
			return;

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
			case VARIABLE:
			{
				variableNodeToString(nextNode);
				break;
			}
			case IF:
			{
				ifNodeToString(nextNode);
				break;
			}
			case WHILE:
			{
				whileNodeToString(nextNode);
				break;
			}
		}
	}

	private int nextCodeLineNumber()
	{
		return mashineCodeString.split(COMMAND_LINE_DELIMITER).length + 1;
	}

	private void printLogicExpressionNode(Node expressionNode)
	{
		switch (expressionNode.getType())
		{
			case MORE_THAN:
			{
				nodeToString(expressionNode.getDependentNode(0));
				nodeToString(expressionNode.getDependentNode(1));
				break;
			}
			case LESS_THAN:
			{
				nodeToString(expressionNode.getDependentNode(1));
				nodeToString(expressionNode.getDependentNode(0));
				break;
			}
		}

		mashineCodeString += "LT";
		mashineCodeString += COMMAND_LINE_DELIMITER;
	}

	private void whileNodeToString(Node whileNode)
	{
		printLogicExpressionNode(whileNode.getDependentNode(0));

		int backLink = nextCodeLineNumber();
		if (whileNode.getDependentNode(1) != null)
		{
			backLink++;

			mashineCodeString += "JNZ " + BLOCK_INSERT_DELIMITER + blockCount;
			mashineCodeString += COMMAND_LINE_DELIMITER;

			configureNewBlock(whileNode.getDependentNode(1), blockCount, backLink, true);

			blockCount++;
		}

		nodeToString(whileNode.getDependentNode(2));
	}

	private void ifNodeToString(Node ifNode)
	{
		printLogicExpressionNode(ifNode.getDependentNode(0));

		int backLink = nextCodeLineNumber();
		if (ifNode.getDependentNode(1) != null)
		{
			backLink++;
		}
		if (ifNode.getDependentNode(2) != null)
		{
			backLink++;
		}
		if (ifNode.getDependentNode(1) != null)
		{
			mashineCodeString += "JNZ " + BLOCK_INSERT_DELIMITER + blockCount;
			mashineCodeString += COMMAND_LINE_DELIMITER;

			configureNewBlock(ifNode.getDependentNode(1), blockCount, backLink, false);

			blockCount++;
		}

		if (ifNode.getDependentNode(2) != null)
		{
			mashineCodeString += "JZ " + BLOCK_INSERT_DELIMITER + blockCount;
			mashineCodeString += COMMAND_LINE_DELIMITER;

			configureNewBlock(ifNode.getDependentNode(2), blockCount, backLink, false);

			blockCount++;
		}

		nodeToString(ifNode.getDependentNode(3));
	}

	private void configureNewBlock(Node blockNode, int blockNumber, int backLink, boolean isWhile)
	{
		String blockCode = new String();

		String buf = mashineCodeString;
		mashineCodeString = "";
		nodeToString(blockNode);
		blockCode = mashineCodeString;
		mashineCodeString = buf;

		if (isWhile)
		{
			backLink -= 4;
		}

		blockCode += "JMP " + backLink;

		Blocks.put(blockNumber, blockCode);
	}

	private void variableNodeToString(Node node)
	{

		if (node.getDependentNode(0).getType() == NodeType.SET)
		{
			nodeToString(node.getDependentNode(1));
			mashineCodeString += "STORE " + node.getValue();
			mashineCodeString += COMMAND_LINE_DELIMITER;
			nodeToString(node.getDependentNode(2));
		}
		else
		{
			mashineCodeString += "FETCH " + node.getValue();
			mashineCodeString += COMMAND_LINE_DELIMITER;
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
