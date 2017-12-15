package compiler;

import nodes.Node;
import nodes.NodeType;

import java.util.*;

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
	private static final String BACK_START = "---BACK_START_";
	private static final String BACK_INSERT_END = "---BACK_END_";

	private String mashineCodeString;
	private int blockCount;
	private Map<Integer, String> blocks;
	private List<String> codeBuffer;

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
		blocks = new HashMap<>();
		codeBuffer = new ArrayList<>();
		nodeToString(rootNode);
		blocksToCode();
		backStartProcessing();
		return mashineCodeString;
	}

	private void backStartProcessing()
	{
		List<String> codeStrings = new ArrayList<>(Arrays.asList(mashineCodeString.split(COMMAND_LINE_DELIMITER)));
		Map<Integer, String> back = new HashMap<>();
		mashineCodeString = "";

		for (int i = 0; i < codeStrings.size(); i++)
		{
			if (codeStrings.get(i).startsWith(BACK_START))
			{
				back.put(i + 1, BACK_INSERT_END + codeStrings.get(i).substring(codeStrings.get(i).lastIndexOf("_") + 1));
				codeStrings.set(i, "");
				i = 0;
			}
		}

		for (String code : codeStrings)
		{
			mashineCodeString += code;
			mashineCodeString += COMMAND_LINE_DELIMITER;
		}

		for (Integer i : back.keySet())
		{
			mashineCodeString = mashineCodeString.replaceAll(back.get(i), i.toString());
		}
	}

	private void blocksToCode()
	{
		for (Integer id : blocks.keySet())
		{
			mashineCodeString = mashineCodeString.replaceAll(BLOCK_INSERT_DELIMITER + id, nextCodeLineNumber(mashineCodeString) + "");
			mashineCodeString += COMMAND_LINE_DELIMITER;
			mashineCodeString += blocks.get(id);
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

	private int nextCodeLineNumber(String code)
	{
		return code.split(COMMAND_LINE_DELIMITER).length + 1;
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
		mashineCodeString += BACK_START + ++blockCount;
		mashineCodeString += COMMAND_LINE_DELIMITER;

		printLogicExpressionNode(whileNode.getDependentNode(0));

		if (whileNode.getDependentNode(1) != null)
		{

			mashineCodeString += "JNZ " + BLOCK_INSERT_DELIMITER + blockCount;
			mashineCodeString += COMMAND_LINE_DELIMITER;

			configureNewBlock(whileNode.getDependentNode(1), blockCount, true);
		}

		nodeToString(whileNode.getDependentNode(2));
	}

	private void ifNodeToString(Node ifNode)
	{
		printLogicExpressionNode(ifNode.getDependentNode(0));

		if (ifNode.getDependentNode(1) != null)
		{
			mashineCodeString += "JNZ " + BLOCK_INSERT_DELIMITER + ++blockCount;
			mashineCodeString += COMMAND_LINE_DELIMITER;
			mashineCodeString += BACK_START + blockCount;
			mashineCodeString += COMMAND_LINE_DELIMITER;

			configureNewBlock(ifNode.getDependentNode(1), blockCount, false);
		}

		if (ifNode.getDependentNode(2) != null)
		{
			mashineCodeString += "JZ " + BLOCK_INSERT_DELIMITER + ++blockCount;
			mashineCodeString += COMMAND_LINE_DELIMITER;
			mashineCodeString += BACK_START + blockCount;
			mashineCodeString += COMMAND_LINE_DELIMITER;

			configureNewBlock(ifNode.getDependentNode(2), blockCount, false);
		}

		nodeToString(ifNode.getDependentNode(3));
	}

	private void configureNewBlock(Node blockNode, int blockNumber, boolean isWhile)
	{
		String blockCode = new String();

		codeBuffer.add(mashineCodeString);
		mashineCodeString = "";
		nodeToString(blockNode);
		blockCode = mashineCodeString;
		mashineCodeString = codeBuffer.get(codeBuffer.size() - 1);
		codeBuffer.remove(codeBuffer.size() - 1);

		blockCode += "JMP " + BACK_INSERT_END + blockNumber;

		blocks.put(blockNumber, blockCode);
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
