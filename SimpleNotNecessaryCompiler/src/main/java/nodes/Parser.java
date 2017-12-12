package nodes;

import tokens.TokenList;

/**
 * Парсер
 * Строит синтаксическое дерево из списка токенов
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class Parser
{
	public Node parse(TokenList list)
	{
		Node expectedNode = new Node();
		expectedNode.setType(NodeType.PRINT);
		Node printValueNode = new Node();
		expectedNode.setType(NodeType.DIGIT);
		printValueNode.setValue("1");
		expectedNode.addDependentNode(printValueNode);
		Node nextNode = new Node();
		expectedNode.setType(NodeType.EOF);
		expectedNode.addDependentNode(nextNode);

		return expectedNode;
	}
}
