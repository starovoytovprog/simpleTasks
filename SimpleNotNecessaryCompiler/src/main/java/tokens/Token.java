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

	/**
	 * Получить тип токена
	 *
	 * @return тип токена
	 */
	public TokenType getType()
	{
		return type;
	}

	/**
	 * Установить тип токена
	 *
	 * @param type тип токена
	 */
	public void setType(TokenType type)
	{
		this.type = type;
	}

	/**
	 * Получить значение токена
	 *
	 * @return значение токена
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Установить значение токена
	 *
	 * @param value значение токена
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Сравнение токенов
	 *
	 * @param token токен, с которым сравнивается текущий
	 * @return true, если токены эквивалентны
	 */
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
