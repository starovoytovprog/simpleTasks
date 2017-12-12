package tokens;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static tokens.TokenType.*;

/**
 * Тестирование лексического анализатора {@link LexicalAnalysis}
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class LexicalAnalysisTest
{
	private static final LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();

	@Test
	public void findPrintCommandTest()
	{
		String programText = "print;";
		TokenList list = lexicalAnalysis.analys(programText);

		TokenList expectedList = new TokenList();
		Token printToken = new Token();
		printToken.setType(PRINT);
		Token semicolon = new Token();
		semicolon.setType(SEMICOLON);

		expectedList.put(printToken);
		expectedList.put(semicolon);

		assertTrue(expectedList.equals(list));
	}

	@Test
	public void printCommandTest()
	{
		String programText = "print(1);";
		TokenList list = lexicalAnalysis.analys(programText);

		TokenList expectedList = new TokenList();
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

		expectedList.put(printToken);
		expectedList.put(lPar);
		expectedList.put(valueToken);
		expectedList.put(rPar);
		expectedList.put(semicolon);

		assertTrue(expectedList.equals(list));
	}
}
