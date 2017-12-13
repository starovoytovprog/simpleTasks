package compiler;

import nodes.Node;

import static vm.Constants.COMMAND_LINE_DELIMITER;

/**
 * Компилятор
 * Строит машинный код из синтаксического дерева
 *
 * @author Starovoytov
 * @since 13.12.2017
 */
public class Compiler
{
	/**
	 * Формирование машинного кода
	 *
	 * @param rootNode корень синтаксического дерева
	 * @return машинный код
	 */
	public String compile(Node rootNode)
	{
		return "PUSH 1" + COMMAND_LINE_DELIMITER +
			"ECHO" + COMMAND_LINE_DELIMITER +
			"HALT";
	}
}
