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

	public TokenList()
	{
		tokens = new ArrayList<>();
	}

	public void put(Token printToken)
	{
		tokens.add(printToken);
	}

	@Override
	public boolean equals(Object list)
	{
		if (list instanceof TokenList)
		{
			return tokens.containsAll(((TokenList) list).tokens) && tokens.size() == ((TokenList) list).tokens.size();
		}

		return false;
	}
}
