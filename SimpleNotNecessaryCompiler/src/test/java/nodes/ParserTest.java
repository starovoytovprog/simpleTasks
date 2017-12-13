package nodes;

import org.junit.Test;
import tokens.Token;
import tokens.TokenList;
import tokens.TokenType;

import static org.junit.Assert.assertTrue;
import static tokens.TokenType.*;

/**
 * Тестирование парсера {@link Parser}
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class ParserTest
{
	private static final Parser parser = new Parser();

	/**
	 * Тест цепочки токенов для печати
	 * @throws Exception синтаксическая ошибка
	 */
	@Test
	public void simplePrintTest() throws Exception
	{
		TokenList list = new TokenList();
		Token printToken = new Token();
		printToken.setType(TokenType.PRINT);
		Token valueToken = new Token();
		valueToken.setType(TokenType.DIGIT);
		valueToken.setValue("1");
		Token lPar = new Token();
		lPar.setType(TokenType.LPAR);
		Token rPar = new Token();
		rPar.setType(TokenType.RPAR);
		Token semicolon = new Token();
		semicolon.setType(TokenType.SEMICOLON);
		Token eof = new Token();
		eof.setType(TokenType.EOF);

		list.put(printToken);
		list.put(lPar);
		list.put(valueToken);
		list.put(rPar);
		list.put(semicolon);
		list.put(eof);

		Node expectedNode = new Node();
		expectedNode.setType(NodeType.PRINT);
		Node printValueNode = new Node();
		printValueNode.setType(NodeType.DIGIT);
		printValueNode.setValue("1");
		expectedNode.addDependentNode(printValueNode);
		Node nextNode = new Node();
		nextNode.setType(NodeType.EOF);
		expectedNode.addDependentNode(nextNode);

		Node parseNode = parser.parse(list);
		assertTrue(expectedNode.equals(parseNode));
	}

	/**
	 * Тест цепочки токенов для печати выражения +
	 * @throws Exception синтаксическая ошибка
	 */
	@Test
	public void simplePrintSumTest() throws Exception
	{
		TokenList list = new TokenList();
		Token printToken = new Token();
		printToken.setType(PRINT);
		Token value1Token = new Token();
		value1Token.setType(DIGIT);
		value1Token.setValue("1");
		Token symbolSum = new Token();
		symbolSum.setType(SUM);
		Token value2Token = new Token();
		value2Token.setType(DIGIT);
		value2Token.setValue("2");
		Token symbolSum2 = new Token();
		symbolSum2.setType(SUM);
		Token value3Token = new Token();
		value3Token.setType(DIGIT);
		value3Token.setValue("3");
		Token lPar = new Token();
		lPar.setType(LPAR);
		Token rPar = new Token();
		rPar.setType(RPAR);
		Token semicolon = new Token();
		semicolon.setType(SEMICOLON);
		Token eof = new Token();
		eof.setType(EOF);

		list.put(printToken);
		list.put(lPar);
		list.put(value1Token);
		list.put(symbolSum);
		list.put(value2Token);
		list.put(symbolSum2);
		list.put(value3Token);
		list.put(rPar);
		list.put(semicolon);
		list.put(eof);

		Node expectedNode = new Node();
		expectedNode.setType(NodeType.PRINT);
		Node sum1Node = new Node();
		sum1Node.setType(NodeType.SUM);
		Node digit1Node = new Node();
		digit1Node.setType(NodeType.DIGIT);
		digit1Node.setValue("1");
		sum1Node.addDependentNode(digit1Node);
		Node sum2Node = new Node();
		sum2Node.setType(NodeType.SUM);
		Node digit2Node = new Node();
		digit2Node.setType(NodeType.DIGIT);
		digit2Node.setValue("2");
		Node digit3Node = new Node();
		digit3Node.setType(NodeType.DIGIT);
		digit3Node.setValue("3");
		sum2Node.addDependentNode(digit2Node);
		sum2Node.addDependentNode(digit3Node);
		sum1Node.addDependentNode(sum2Node);
		expectedNode.addDependentNode(sum1Node);
		Node nextNode = new Node();
		nextNode.setType(NodeType.EOF);
		expectedNode.addDependentNode(nextNode);

		Node parseNode = parser.parse(list);
		assertTrue(expectedNode.equals(parseNode));
	}
}
