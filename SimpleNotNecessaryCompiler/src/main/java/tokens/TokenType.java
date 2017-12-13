package tokens;

/**
 * Тип токена
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public enum TokenType
{
	/**
	 * Токен команды печать
	 */
	PRINT,
	/**
	 * Числовая константа
	 */
	DIGIT,
	/**
	 * Открывающаяся скобка
	 */
	LPAR,
	/**
	 * Закрывающаяся скобка
	 */
	RPAR,
	/**
	 * точка с запятой
	 */
	SEMICOLON,
	/**
	 * конец файла
	 */
	EOF,
	/**
	 * операция сложения
	 */
	SUM
}
