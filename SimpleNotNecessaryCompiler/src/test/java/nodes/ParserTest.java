package nodes;

import org.junit.Test;
import tokens.Token;
import tokens.TokenList;

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
	 */
	@Test
	public void simplePrintTest()
	{
		TokenList list = new TokenList();
		Token printToken = new Token();
		printToken.setType(PRINT);
		Token valueToken = new Token();
		valueToken.setType(DIGIT);
		valueToken.setValue("1");
		Token lPar = new Token();
		lPar.setType(LPAR);
		Token rPar = new Token();
		rPar.setType(RPAR);
		Token semicolon = new Token();
		semicolon.setType(SEMICOLON);

		list.put(printToken);
		list.put(lPar);
		list.put(valueToken);
		list.put(rPar);
		list.put(semicolon);

		Node expectedNode = new Node();
	}
}
