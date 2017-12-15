package nodes;

/**
 * Тип ноды
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public enum NodeType
{
	/**
	 * Нода печати
	 */
	PRINT,
	/**
	 * Число
	 */
	DIGIT,
	/**
	 * Конец выполнения программы
	 */
	EOF,
	/**
	 * Операция сложения
	 */
	SUM,
	/**
	 * Операция вычитания
	 */
	MINUS,
	/**
	 * Выражение
	 */
	EXPRESSION,
	/**
	 * Выражение
	 */
	VARIABLE,
	/**
	 * Установить
	 */
	SET,
	/**
	 * Вернуть
	 */
	GET,
	/**
	 * Если
	 */
	IF,
	/**
	 * Больше чем
	 */
	MORE_THAN,
	/**
	 * Меньше чем
	 */
	LESS_THAN
}
