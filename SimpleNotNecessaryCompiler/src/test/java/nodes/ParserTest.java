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
public class ParserTest {
	private static final Parser parser = new Parser();

	/**
	 * Тест цепочки токенов для печати
	 *
	 * @throws Exception синтаксическая ошибка
	 */
	@Test
	public void simplePrintTest() throws Exception {
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
	 *
	 * @throws Exception синтаксическая ошибка
	 */
	@Test
	public void simplePrintSumTest() throws Exception {
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

		Node parseNode = parser.parse(list);
		assertTrue(expectedNode.equals(parseNode));
	}

	/**
	 * Тест цепочки токенов для определения переменной
	 *
	 * @throws Exception синтаксическая ошибка
	 */
	@Test
	public void simpleVariableTest() throws Exception {
		TokenList list = new TokenList();
		Token variableToken = new Token();
		variableToken.setType(VARIABLE);
		variableToken.setValue("x");
		Token setToken = new Token();
		setToken.setType(SET);
		Token valueToken = new Token();
		valueToken.setType(DIGIT);
		valueToken.setValue("5");
		Token semicolon = new Token();
		semicolon.setType(SEMICOLON);
		Token eof = new Token();
		eof.setType(EOF);
		list.put(variableToken);
		list.put(setToken);
		list.put(valueToken);
		list.put(semicolon);
		list.put(eof);

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

		Node parseNode = parser.parse(list);
		assertTrue(expectedNode.equals(parseNode));
	}

	/**
	 * Тест цепочки токенов для простого условия
	 *
	 * @throws Exception синтаксическая ошибка
	 */
	@Test
	public void simpleIfTest() throws Exception {
		TokenList list = new TokenList();

		Token ifToken = new Token();
		ifToken.setType(IF);
		Token value1Token = new Token();
		value1Token.setType(DIGIT);
		value1Token.setValue("10");
		Token moreThanToken = new Token();
		moreThanToken.setType(MORE_THAN);
		Token value2Token = new Token();
		value2Token.setType(DIGIT);
		value2Token.setValue("5");
		Token semicolon = new Token();
		semicolon.setType(SEMICOLON);
		Token eof = new Token();
		eof.setType(EOF);
		Token lPar = new Token();
		lPar.setType(LPAR);
		Token rPar = new Token();
		rPar.setType(RPAR);
		Token printToken = new Token();
		printToken.setType(PRINT);
		Token value3Token = new Token();
		value3Token.setType(DIGIT);
		value3Token.setValue("1");
		Token lPar2 = new Token();
		lPar2.setType(LPAR);
		Token rPar2 = new Token();
		rPar2.setType(RPAR);
		Token spaceToken = new Token();
		spaceToken.setType(SPACE);
		Token lbra = new Token();
		lbra.setType(LBRA);
		Token rbra = new Token();
		rbra.setType(RBRA);
		list.put(ifToken);
		list.put(lPar);
		list.put(value1Token);
		list.put(moreThanToken);
		list.put(value2Token);
		list.put(rPar);
		list.put(spaceToken);
		list.put(lbra);
		list.put(printToken);
		list.put(lPar2);
		list.put(value3Token);
		list.put(rPar2);
		list.put(semicolon);
		list.put(rbra);
		list.put(eof);

		Node ifNode = new Node();
		ifNode.setType(NodeType.IF);
		Node moreNode = new Node();
		moreNode.setType(NodeType.MORE_THAN);
		Node digit1 = new Node();
		digit1.setType(NodeType.DIGIT);
		digit1.setValue("10");
		moreNode.addDependentNode(digit1);
		Node digit2 = new Node();
		digit2.setType(NodeType.DIGIT);
		digit2.setValue("5");
		moreNode.addDependentNode(digit2);
		ifNode.addDependentNode(moreNode);
		Node thrueNode = new Node();
		thrueNode.setType(NodeType.PRINT);
		Node printValueNode = new Node();
		printValueNode.setType(NodeType.DIGIT);
		printValueNode.setValue("1");
		thrueNode.addDependentNode(printValueNode);
		thrueNode.addDependentNode(null);
		ifNode.addDependentNode(thrueNode);
		Node falseNode = null;
		ifNode.addDependentNode(falseNode);
		Node nextNode = new Node();
		nextNode.setType(NodeType.EOF);
		ifNode.addDependentNode(nextNode);

		Node parseNode = parser.parse(list);
		assertTrue(ifNode.equals(parseNode));
	}

	/**
	 * Тест цепочки токенов для цикла
	 *
	 * @throws Exception синтаксическая ошибка
	 */
	@Test
	public void simpleWhileTest() throws Exception {
		TokenList list = new TokenList();

		Token ifToken = new Token();
		ifToken.setType(WHILE);
		Token value1Token = new Token();
		value1Token.setType(DIGIT);
		value1Token.setValue("1");
		Token moreThanToken = new Token();
		moreThanToken.setType(MORE_THAN);
		Token value2Token = new Token();
		value2Token.setType(DIGIT);
		value2Token.setValue("5");
		Token semicolon = new Token();
		semicolon.setType(SEMICOLON);
		Token eof = new Token();
		eof.setType(EOF);
		Token lPar = new Token();
		lPar.setType(LPAR);
		Token rPar = new Token();
		rPar.setType(RPAR);
		Token printToken = new Token();
		printToken.setType(PRINT);
		Token value3Token = new Token();
		value3Token.setType(DIGIT);
		value3Token.setValue("1");
		Token lPar2 = new Token();
		lPar2.setType(LPAR);
		Token rPar2 = new Token();
		rPar2.setType(RPAR);
		Token spaceToken = new Token();
		spaceToken.setType(SPACE);
		Token lbra = new Token();
		lbra.setType(LBRA);
		Token rbra = new Token();
		rbra.setType(RBRA);
		list.put(ifToken);
		list.put(lPar);
		list.put(value1Token);
		list.put(moreThanToken);
		list.put(value2Token);
		list.put(rPar);
		list.put(spaceToken);
		list.put(lbra);
		list.put(printToken);
		list.put(lPar2);
		list.put(value3Token);
		list.put(rPar2);
		list.put(semicolon);
		list.put(rbra);
		list.put(eof);

		Node whileNode = new Node();
		whileNode.setType(NodeType.WHILE);
		Node moreNode = new Node();
		moreNode.setType(NodeType.MORE_THAN);
		Node digit1 = new Node();
		digit1.setType(NodeType.DIGIT);
		digit1.setValue("1");
		moreNode.addDependentNode(digit1);
		Node digit2 = new Node();
		digit2.setType(NodeType.DIGIT);
		digit2.setValue("5");
		moreNode.addDependentNode(digit2);
		whileNode.addDependentNode(moreNode);
		Node thrueNode = new Node();
		thrueNode.setType(NodeType.PRINT);
		Node printValueNode = new Node();
		printValueNode.setType(NodeType.DIGIT);
		printValueNode.setValue("1");
		thrueNode.addDependentNode(printValueNode);
		thrueNode.addDependentNode(null);
		whileNode.addDependentNode(thrueNode);
		Node nextNode = new Node();
		nextNode.setType(NodeType.EOF);
		whileNode.addDependentNode(nextNode);

		Node parseNode = parser.parse(list);
		assertTrue(whileNode.equals(parseNode));
	}
}
