package tokens;

/**
 * Токен
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class Token
{
	private TokenType type;
	private String value = "";

	public void setType(TokenType type)
	{
		this.type = type;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public boolean equals(Object token)
	{
		if (token instanceof Token)
		{
			return type == ((Token) token).type && value.equals(((Token) token).value);
		}

		return false;
	}
}
