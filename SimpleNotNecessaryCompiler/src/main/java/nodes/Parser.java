package nodes;

import tokens.Token;
import tokens.TokenList;
import tokens.TokenType;

/**
 * Парсер
 * Строит синтаксическое дерево из списка токенов
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class Parser
{
	/**
	 * Построение дерева
	 *
	 * @param list список токенов
	 * @return корневой узел
	 * @throws Exception синтаксическая ошибка
	 */
	public Node parse(TokenList list) throws Exception
	{
		Node rootNode = getNextNode(list);
		return rootNode;
	}

	/**
	 * Рекурсивное построение дерева
	 *
	 * @param list список токенов
	 * @return следующий узел
	 * @throws Exception синтаксическая ошибка
	 */
	private Node getNextNode(TokenList list) throws Exception
	{
		Token nextToken = list.pop();
		Node currentNode = new Node();

		switch (nextToken.getType())
		{
			case PRINT:
			{
				currentNode = configurePrintNode(currentNode, list, nextToken);
				break;
			}
			case LPAR:
			{
				currentNode = configureLparNode(currentNode, list, nextToken);
				break;
			}
			case DIGIT:
			{
				currentNode = configureDigitNode(currentNode, list, nextToken);
				break;
			}
			case EOF:
			{
				currentNode = configureEofNode(currentNode, list, nextToken);
				break;
			}
		}

		return currentNode;
	}

	private Node configurePrintNode(Node printNode, TokenList list, Token nextToken) throws Exception
	{
		printNode.setType(NodeType.PRINT);
		Node printValueNode = getNextNode(list);
		printNode.addDependentNode(printValueNode);

		if (list.pop().getType() != TokenType.SEMICOLON)
		{
			throw new Exception("bad syntaxis");
		}

		Node nextNode = getNextNode(list);
		printNode.addDependentNode(nextNode);

		return printNode;
	}

	private Node configureLparNode(Node lparNode, TokenList list, Token nextToken) throws Exception
	{
		Node lparValue = getNextNode(list);

		if (list.pop().getType() != TokenType.RPAR)
		{
			throw new Exception("bad syntaxis");
		}

		return lparValue;
	}

	private Node configureDigitNode(Node digitNode, TokenList list, Token nextToken)
	{
		digitNode.setType(NodeType.DIGIT);
		digitNode.setValue(nextToken.getValue());

		return digitNode;
	}

	private Node configureEofNode(Node eofNode, TokenList list, Token nextToken)
	{
		eofNode.setType(NodeType.EOF);
		return eofNode;
	}
}
