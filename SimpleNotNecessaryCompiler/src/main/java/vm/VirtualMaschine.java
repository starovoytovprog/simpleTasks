package vm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static vm.Constants.COMMAND_LINE_DELIMITER;

/**
 * Виртуальная машина, исполняющая машинный-код
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class VirtualMaschine
{
	private final Stack<String> programStack;
	private final Map<String, String> variablesMap;

	/**
	 * Конструктор по умолчанию
	 */
	public VirtualMaschine()
	{
		programStack = new Stack<>();
		variablesMap = new HashMap<>();
	}

	/**
	 * Исполнение машинного кода
	 *
	 * @param mashineCode машинный код программы
	 * @exception Exception исключения при ошибках в машинном коде
	 */
	public void run(String mashineCode) throws Exception
	{
		String[] commands = mashineCode.split(COMMAND_LINE_DELIMITER);

		int res;
		for (int i = 0; i < commands.length; i++)
		{
			res = executeCommand(commands[i].trim());

			if (res == -1)
			{
				return;
			}

			if (res > 0)
			{
				i = res - 2;
			}
		}
	}

	/**
	 * Выполнение строки команды
	 *
	 * @param commandString команда
	 * @exception Exception ошибка в команде
	 */
	private int executeCommand(String commandString) throws Exception
	{
		if (commandString.isEmpty())
		{
			return 0;
		}

		VmCommands command = VmCommands.valueOf(commandString.split(" ")[0]);

		switch (command)
		{
			case ECHO:
			{
				echoCommand();
				break;
			}
			case PUSH:
			{
				pushCommand(commandString);
				break;
			}
			case POP:
			{
				popCommand();
				break;
			}
			case ADD:
			{
				addCommand();
				break;
			}
			case SUB:
			{
				subCommand();
				break;
			}
			case STORE:
			{
				storeCommand(commandString);
				break;
			}
			case FETCH:
			{
				fetchCommand(commandString);
				break;
			}
			case HALT:
			{
				return -1;
			}
			case JMP:
			{
				return jmpCommand(commandString);
			}
			case LT:
			{
				ltCommand();
				break;
			}
			case EQ:
			{
				eqCommand();
				break;
			}
			case JZ:
			{
				return jzCommand(commandString);
			}
			case JNZ:
			{
				return jnzCommand(commandString);
			}
		}

		return 0;
	}

	/**
	 * Выполнение команды вывода
	 */
	private void echoCommand()
	{
		System.out.println(programStack.pop());
	}

	/**
	 * Команда добавления значения в стек
	 *
	 * @param commandString строка команды
	 */
	private void pushCommand(String commandString)
	{
		programStack.push(commandString.substring(5));
	}

	/**
	 * Команда удаления значения из стека
	 */
	private void popCommand()
	{
		programStack.pop();
	}

	/**
	 * Команда сложения значений в стеке
	 */
	private void addCommand()
	{
		int value_1 = Integer.parseInt(programStack.pop());
		int value_2 = Integer.parseInt(programStack.pop());

		programStack.push((value_1 + value_2) + "");
	}

	/**
	 * Команда вычитания значений в стеке
	 */
	private void subCommand()
	{
		int value_1 = Integer.parseInt(programStack.pop());
		int value_2 = Integer.parseInt(programStack.pop());

		programStack.push((value_1 - value_2) + "");
	}

	/**
	 * Команда сохранения значения из стека в переменной
	 *
	 * @param commandString строка команды
	 */
	private void storeCommand(String commandString)
	{
		variablesMap.put(commandString.split(" ")[1], programStack.pop());
	}

	/**
	 * Команда добавления значения из переменной в стек
	 *
	 * @param commandString строка команды
	 */
	private void fetchCommand(String commandString)
	{
		programStack.push(variablesMap.get(commandString.split(" ")[1]));
	}

	/**
	 * Команда безусловного перехода
	 *
	 * @param commandString строка команды
	 */
	private int jmpCommand(String commandString)
	{
		return Integer.parseInt(commandString.split(" ")[1]);
	}

	/**
	 * Команда сравнения значений в стеке меньше чем
	 */
	private void ltCommand()
	{
		int value_1 = Integer.parseInt(programStack.pop());
		int value_2 = Integer.parseInt(programStack.pop());

		if (value_1 < value_2)
		{
			programStack.push("1");
		}
		else
		{
			programStack.push("0");
		}
	}

	/**
	 * Команда сравнения значений в стеке равно
	 */
	private void eqCommand()
	{
		int value_1 = Integer.parseInt(programStack.pop());
		int value_2 = Integer.parseInt(programStack.pop());

		if (value_1 == value_2)
		{
			programStack.push("1");
		}
		else
		{
			programStack.push("0");
		}
	}

	/**
	 * Команда условного перехода, если в стеке 0
	 *
	 * @param commandString строка команды
	 */
	private int jzCommand(String commandString)
	{
		if (Integer.parseInt(programStack.peek()) == 0)
		{
			return Integer.parseInt(commandString.split(" ")[1]);
		}

		return 0;
	}

	/**
	 * Команда условного перехода, если в стеке не 0
	 *
	 * @param commandString строка команды
	 */
	private int jnzCommand(String commandString)
	{
		if (Integer.parseInt(programStack.peek()) != 0)
		{
			return Integer.parseInt(commandString.split(" ")[1]);
		}

		return 0;
	}
}
