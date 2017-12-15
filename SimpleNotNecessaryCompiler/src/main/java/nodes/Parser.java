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
			case IF:
			{
				currentNode = configureIfNode(currentNode, list, nextToken);
				break;
			}
			case LPAR:
			{
				currentNode = configureExpressionNode(list, TokenType.RPAR);
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
			case SPACE:
			{
				currentNode = getNextNode(list);
				break;
			}
			case SEMICOLON:
			{
				currentNode = getNextNode(list);
				break;
			}
			case VARIABLE:
			{
				currentNode = configureVariableNode(currentNode, list, nextToken);
				break;
			}
			case RBRA:
			{
				currentNode = null;
				break;
			}
		}

		return currentNode;
	}

	private Node configureIfNode(Node ifNode, TokenList list, Token nextToken) throws Exception
	{
		ifNode.setType(NodeType.IF);
		Node conditionNode = getNextNode(list);
		ifNode.addDependentNode(conditionNode);

		Token t = list.popNotSpace();

		if (t.getType() == TokenType.LBRA)
		{
			list.pop();
			Node trueNode = getNextNode(list);
			ifNode.addDependentNode(trueNode);
		}

		t = list.popNotSpace();

		if (t.getType() == TokenType.ELSE)
		{
			list.pop();
			Node falseNode = getNextNode(list);
			ifNode.addDependentNode(falseNode);
		}
		else
		{
			ifNode.addDependentNode(null);
		}

		Node nextNode = getNextNode(list);
		ifNode.addDependentNode(nextNode);

		return ifNode;
	}

	private Node configurePrintNode(Node printNode, TokenList list, Token nextToken) throws Exception
	{
		printNode.setType(NodeType.PRINT);
		Node printValueNode = getNextNode(list);
		printNode.addDependentNode(printValueNode);

		Token t = list.popNotSpace();

		if (t.getType() != TokenType.SEMICOLON)
		{
			throw new Exception("bad syntaxis");
		}

		Node nextNode = getNextNode(list);
		printNode.addDependentNode(nextNode);

		return printNode;
	}

	private Node configureExpressionNode(TokenList list, TokenType outTokenType) throws Exception
	{
		Node expressionNode = new Node();
		expressionNode.setType(NodeType.EXPRESSION);

		Token nextToken;

		while (true)
		{
			nextToken = list.pop();

			switch (nextToken.getType())
			{
				case SPACE:
				{
					continue;
				}
				case LPAR:
				{
					expressionNode.addDependentNode(configureExpressionNode(list, TokenType.RPAR));
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
				case MORE_THAN:
				{
					Node n = new Node();
					n.setType(NodeType.MORE_THAN);
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
				case VARIABLE:
				{
					Node n = new Node();
					n.setType(NodeType.VARIABLE);
					n.setValue(nextToken.getValue());

					Node n2 = new Node();
					n2.setType(NodeType.GET);

					n.addDependentNode(n2);

					expressionNode.addDependentNode(n);
					break;
				}
				default:
				{
					if (nextToken.getType() == outTokenType)
					{
						return rollUpExpression(expressionNode);
					}
				}
			}
		}
	}

	private Node rollUpExpression(Node expressionNode)
	{
		return rollUpMoreThanExpression(
			rollUpSumExpression(
				rollUpMinusExpression(
					expressionNode
				)
			)
		).getDependentNode(0);
	}

	private Node rollUpNodeTypeExpression(Node expressionNode, NodeType rollType)
	{
		List<Node> l = expressionNode.getDependentNodes();

		for (int i = 1; i < l.size() - 1; i++)
		{
			if (l.get(i).getType() == rollType && l.get(i).getDependentNodes().isEmpty())
			{
				Node minusNode = new Node();
				minusNode.setType(rollType);
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

	private Node rollUpMoreThanExpression(Node expressionNode)
	{
		return rollUpNodeTypeExpression(expressionNode, NodeType.MORE_THAN);
	}

	private Node rollUpMinusExpression(Node expressionNode)
	{
		return rollUpNodeTypeExpression(expressionNode, NodeType.MINUS);
	}

	private Node rollUpSumExpression(Node expressionNode)
	{
		return rollUpNodeTypeExpression(expressionNode, NodeType.SUM);
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

	private Node configureVariableNode(Node variableNode, TokenList list, Token nextToken) throws Exception
	{
		variableNode.setType(NodeType.VARIABLE);
		variableNode.setValue(nextToken.getValue());

		Token doToken = list.popNotSpace();

		if (doToken.getType() == TokenType.SET)
		{
			Node setNode = new Node();
			setNode.setType(NodeType.SET);
			variableNode.addDependentNode(setNode);

			Node valueNode = configureExpressionNode(list, TokenType.SEMICOLON);
			variableNode.addDependentNode(valueNode);
		}
		else
		{
			Node getNode = new Node();
			getNode.setType(NodeType.GET);
			variableNode.addDependentNode(getNode);
			list.back();
		}

		Node nextNode = getNextNode(list);
		variableNode.addDependentNode(nextNode);

		return variableNode;
	}
}
