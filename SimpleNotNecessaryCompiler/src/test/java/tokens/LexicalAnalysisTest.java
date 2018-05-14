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
public class LexicalAnalysisTest {
	private static final LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();

	/**
	 * Тест распознавания команды печати
	 */
	@Test
	public void printCommandTest() {
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
		Token eof = new Token();
		eof.setType(EOF);

		expectedList.put(printToken);
		expectedList.put(lPar);
		expectedList.put(valueToken);
		expectedList.put(rPar);
		expectedList.put(semicolon);
		expectedList.put(eof);

		assertTrue(expectedList.equals(list));
	}

	/**
	 * Тест распознавания команды печати с вычисляемым выражением +
	 */
	@Test
	public void printSummCommandTest() {
		String programText = "print(1+2+3);";
		TokenList list = lexicalAnalysis.analys(programText);

		TokenList expectedList = new TokenList();
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

		expectedList.put(printToken);
		expectedList.put(lPar);
		expectedList.put(value1Token);
		expectedList.put(symbolSum);
		expectedList.put(value2Token);
		expectedList.put(symbolSum2);
		expectedList.put(value3Token);
		expectedList.put(rPar);
		expectedList.put(semicolon);
		expectedList.put(eof);

		assertTrue(expectedList.equals(list));
	}

	/**
	 * Тест установки значения пременных
	 */
	@Test
	public void setVariableTest() {
		String programText = "x=5;";
		TokenList list = lexicalAnalysis.analys(programText);

		TokenList expectedList = new TokenList();
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
		expectedList.put(variableToken);
		expectedList.put(setToken);
		expectedList.put(valueToken);
		expectedList.put(semicolon);
		expectedList.put(eof);

		assertTrue(expectedList.equals(list));
	}

	/**
	 * Тест выражения сравнения больше чем
	 */
	@Test
	public void setCompareMoreThanTest() {
		String programText = "if(10>5) {print(1);}";
		TokenList list = lexicalAnalysis.analys(programText);

		TokenList expectedList = new TokenList();

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

		expectedList.put(ifToken);
		expectedList.put(lPar);
		expectedList.put(value1Token);
		expectedList.put(moreThanToken);
		expectedList.put(value2Token);
		expectedList.put(rPar);
		expectedList.put(spaceToken);
		expectedList.put(lbra);
		expectedList.put(printToken);
		expectedList.put(lPar2);
		expectedList.put(value3Token);
		expectedList.put(rPar2);
		expectedList.put(semicolon);
		expectedList.put(rbra);
		expectedList.put(eof);

		assertTrue(expectedList.equals(list));
	}

	/**
	 * Тест выражения цикла
	 */
	@Test
	public void setWhileTest() {
		String programText = "while(1>5) {print(1);}";
		TokenList list = lexicalAnalysis.analys(programText);

		TokenList expectedList = new TokenList();

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

		expectedList.put(ifToken);
		expectedList.put(lPar);
		expectedList.put(value1Token);
		expectedList.put(moreThanToken);
		expectedList.put(value2Token);
		expectedList.put(rPar);
		expectedList.put(spaceToken);
		expectedList.put(lbra);
		expectedList.put(printToken);
		expectedList.put(lPar2);
		expectedList.put(value3Token);
		expectedList.put(rPar2);
		expectedList.put(semicolon);
		expectedList.put(rbra);
		expectedList.put(eof);

		assertTrue(expectedList.equals(list));
	}
}
