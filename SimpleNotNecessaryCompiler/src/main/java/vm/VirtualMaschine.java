package vm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static vm.Constants.COMMAND_LINE_DELIMITER;

/**
 * Виртуальная машина, исполняющая байт-код
 *
 * @author Starovoytov
 * @since 12.12.2017
 */
public class VirtualMaschine
{
	private final Stack<String> programStack;
	private final Map<String, String> variablesMap;

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
		VmCommands command = VmCommands.valueOf(commandString.split(" ")[0]);

		switch (command)
		{
			case ECHO:
			{
				echoCommand(commandString);
				break;
			}
			case PUSH:
			{
				pushCommand(commandString);
				break;
			}
			case POP:
			{
				popCommand(commandString);
				break;
			}
			case ADD:
			{
				addCommand(commandString);
				break;
			}
			case SUB:
			{
				subCommand(commandString);
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
				ltCommand(commandString);
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

	private void echoCommand(String commandString)
	{
		System.out.println(programStack.pop());
	}

	private void pushCommand(String commandString)
	{
		programStack.push(commandString.substring(5));
	}

	private void popCommand(String commandString)
	{
		programStack.pop();
	}

	private void addCommand(String commandString)
	{
		int value_1 = Integer.parseInt(programStack.pop());
		int value_2 = Integer.parseInt(programStack.pop());

		programStack.push((value_1 + value_2) + "");
	}

	private void subCommand(String commandString)
	{
		int value_1 = Integer.parseInt(programStack.pop());
		int value_2 = Integer.parseInt(programStack.pop());

		programStack.push((value_1 - value_2) + "");
	}

	private void storeCommand(String commandString)
	{
		variablesMap.put(commandString.split(" ")[1], programStack.pop());
	}

	private void fetchCommand(String commandString)
	{
		programStack.push(variablesMap.get(commandString.split(" ")[1]));
	}

	private int jmpCommand(String commandString)
	{
		return Integer.parseInt(commandString.split(" ")[1]);
	}

	private void ltCommand(String commandString)
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

	private int jzCommand(String commandString)
	{
		if (Integer.parseInt(programStack.pop()) == 0)
		{
			return Integer.parseInt(commandString.split(" ")[1]);
		}

		return 0;
	}

	private int jnzCommand(String commandString)
	{
		if (Integer.parseInt(programStack.pop()) != 0)
		{
			return Integer.parseInt(commandString.split(" ")[1]);
		}

		return 0;
	}
}
