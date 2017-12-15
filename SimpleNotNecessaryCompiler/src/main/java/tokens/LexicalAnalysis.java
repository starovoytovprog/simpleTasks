package tokens;

import java.util.ArrayList;
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
	private static final List<Character> SYMBOLS = Arrays.asList(';', '(', ')', '+', '-', '\r', '\n', ' ', '=', '>', '{', '}', '<');

	/**
	 * Обработка исходного кода
	 *
	 * @param programText текст программы
	 * @return список токенов
	 */
	public TokenList analys(String programText)
	{
		List<String> keyWords = getKeyWords();

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
					else if (keyWords.contains(tokenWord.toUpperCase()))
					{
						bufToken.setType(TokenType.valueOf(tokenWord.toUpperCase()));
					}
					else
					{
						bufToken.setType(VARIABLE);
						bufToken.setValue(tokenWord);
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

		bufToken = new Token();
		bufToken.setType(EOF);
		list.put(bufToken);

		return list;
	}

	private List<String> getKeyWords()
	{
		List<String> words = new ArrayList<>();

		for (TokenType type : TokenType.values())
		{
			words.add(type.toString());
		}

		return words;
	}

	private Token getCharToken(char ch)
	{
		Token t = new Token();

		switch (ch)
		{
			case ' ':
			{
				t.setType(SPACE);
				break;
			}
			case '\r':
			case '\n':
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
			case '+':
			{
				t.setType(SUM);
				break;
			}
			case '-':
			{
				t.setType(MINUS);
				break;
			}
			case '=':
			{
				t.setType(SET);
				break;
			}
			case '>':
			{
				t.setType(MORE_THAN);
				break;
			}
			case '<':
			{
				t.setType(LESS_THAN);
				break;
			}
			case '{':
			{
				t.setType(LBRA);
				break;
			}
			case '}':
			{
				t.setType(RBRA);
				break;
			}
		}

		return t;
	}
}
