package tokens;

import java.util.ArrayList;
import java.util.List;

/**
 * Список токенов
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class TokenList
{
	private final List<Token> tokens;
	private int i;

	/**
	 * Конструктор по умолчанию
	 */
	public TokenList()
	{
		tokens = new ArrayList<>();
		i = 0;
	}

	/**
	 * Добавить токен в список
	 *
	 * @param printToken новый токен
	 */
	public void put(Token printToken)
	{
		tokens.add(printToken);
	}

	/**
	 * Взять следующий токен из списка
	 *
	 * @return токен из списка
	 */
	public Token pop()
	{
		return tokens.get(i++);
	}

	/**
	 * Взять следующий токен не SPACE из списка
	 *
	 * @return токен из списка
	 */
	public Token popNotSpace()
	{
		for (; i < tokens.size(); i++)
		{
			if (tokens.get(i).getType() != TokenType.SPACE)
			{
				return tokens.get(i);
			}
		}

		return null;
	}

	/**
	 * Сравнение списка токенов
	 *
	 * @param list список, с которым сравнивается текущий
	 * @return true, если списки эквивалентны
	 */
	@Override
	public boolean equals(Object list)
	{
		if (list instanceof TokenList)
		{
			return tokens.containsAll(((TokenList) list).tokens) && tokens.size() == ((TokenList) list).tokens.size();
		}

		return false;
	}

	/**
	 * Вернуться на один шаг в списке
	 */
	public void back()
	{
		i--;
	}
}
