package nodes;

import tokens.Token;
import tokens.TokenList;
import tokens.TokenType;

import java.util.List;

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
				currentNode = configureLparNode(list);
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

	private Node configureLparNode(TokenList list) throws Exception
	{
		Node expressionNode = new Node();
		expressionNode.setType(NodeType.EXPRESSION);

		Token nextToken;

		while (true)
		{
			nextToken = list.pop();

			switch (nextToken.getType())
			{
				case RPAR:
				{
					return rollUpExpression(expressionNode);
				}
				case LPAR:
				{
					expressionNode.addDependentNode(configureLparNode(list));
					break;
				}
				case DIGIT:
				{
					Node n = new Node();
					n.setType(NodeType.DIGIT);
					n.setValue(nextToken.getValue());
					expressionNode.addDependentNode(n);
					break;
				}
				case SUM:
				{
					Node n = new Node();
					n.setType(NodeType.SUM);
					expressionNode.addDependentNode(n);
					break;
				}
				case MINUS:
				{
					Node n = new Node();
					n.setType(NodeType.MINUS);
					expressionNode.addDependentNode(n);
					break;
				}
			}
		}
	}

	private Node rollUpExpression(Node expressionNode)
	{
		return rollUpSumExpression(
			rollUpMinusExpression(
				expressionNode
			)
		).getDependentNode(0);
	}

	private Node rollUpMinusExpression(Node expressionNode)
	{
		List<Node> l = expressionNode.getDependentNodes();

		for (int i = 1; i < l.size() - 1; i++)
		{
			if (l.get(i).getType() == NodeType.MINUS && l.get(i).getDependentNodes().isEmpty())
			{
				Node minusNode = new Node();
				minusNode.setType(NodeType.MINUS);
				minusNode.addDependentNode(l.get(i - 1));
				minusNode.addDependentNode(l.get(i + 1));

				l.set(i - 1, minusNode);
				l.remove(i);
				l.remove(i);
				i = 1;
			}
		}

		return expressionNode;
	}

	private Node rollUpSumExpression(Node expressionNode)
	{
		List<Node> l = expressionNode.getDependentNodes();

		for (int i = 1; i < l.size() - 1; i++)
		{
			if (l.get(i).getType() == NodeType.SUM && l.get(i).getDependentNodes().isEmpty())
			{
				Node minusNode = new Node();
				minusNode.setType(NodeType.SUM);
				minusNode.addDependentNode(l.get(i - 1));
				minusNode.addDependentNode(l.get(i + 1));

				l.set(i - 1, minusNode);
				l.remove(i);
				l.remove(i);
				i = 0;
			}
		}

		return expressionNode;
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
