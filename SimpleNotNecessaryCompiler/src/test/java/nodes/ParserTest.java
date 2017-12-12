package nodes;

import org.junit.Test;
import tokens.Token;
import tokens.TokenList;
import tokens.TokenType;

import static org.junit.Assert.assertTrue;

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
	 */
	@Test
	public void simplePrintTest()
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

		list.put(printToken);
		list.put(lPar);
		list.put(valueToken);
		list.put(rPar);
		list.put(semicolon);

		Node expectedNode = new Node();
		expectedNode.setType(NodeType.PRINT);
		Node printValueNode = new Node();
		expectedNode.setType(NodeType.DIGIT);
		printValueNode.setValue("1");
		expectedNode.addDependentNode(printValueNode);
		Node nextNode = new Node();
		expectedNode.setType(NodeType.EOF);
		expectedNode.addDependentNode(nextNode);

		Node parseNode = parser.parse(list);
		assertTrue(expectedNode.equals(parseNode));
	}
}
