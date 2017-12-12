package tokens;

import java.util.Arrays;
import java.util.List;

import static tokens.TokenType.*;

/**
 * Лексический анализатор
 * Преобразует исходный код в список токенов
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class LexicalAnalysis
{
	private static final List<Character> SYMBOLS = Arrays.asList(';', '(', ')');

	/**
	 * Обработка исходного кода
	 *
	 * @param programText текст программы
	 * @return список токенов
	 */
	public TokenList analys(String programText)
	{
		TokenList list = new TokenList();
		Token bufToken = new Token();
		String tokenWord = "";

		for (char ch : programText.toCharArray())
		{
			if (SYMBOLS.indexOf(ch) > -1)
			{
				if (!tokenWord.isEmpty())
				{
					if (tokenWord.matches("\\d+"))
					{
						bufToken.setType(DIGIT);
						bufToken.setValue(tokenWord);
					}
					else
					{
						bufToken.setType(TokenType.valueOf(tokenWord.toUpperCase()));
					}

					list.put(bufToken);
					bufToken = new Token();
					tokenWord = "";
				}

				list.put(getCharToken(ch));
				continue;
			}

			tokenWord += ch;
		}

		return list;
	}

	private Token getCharToken(char ch)
	{
		Token t = new Token();

		switch (ch)
		{
			case ';':
			{
				t.setType(SEMICOLON);
				break;
			}
			case '(':
			{
				t.setType(LPAR);
				break;
			}
			case ')':
			{
				t.setType(RPAR);
				break;
			}
		}

		return t;
	}
}
