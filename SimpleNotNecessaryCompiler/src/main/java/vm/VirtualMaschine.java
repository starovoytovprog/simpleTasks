package vm;

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

	public VirtualMaschine()
	{
		programStack = new Stack<>();
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

		for (String command : commands)
		{
			executeCommand(command.trim());
		}
	}

	/**
	 * Выполнение строки команды
	 *
	 * @param commandString команда
	 * @exception Exception ошибка в команде
	 */
	private void executeCommand(String commandString) throws Exception
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
		}
	}

	private void echoCommand(String commandString)
	{
		if (commandString.length() == 4)
		{
			System.out.println(programStack.pop());
		}
		else
		{
			System.out.println(commandString.substring(5));
		}
	}

	private void pushCommand(String commandString)
	{
		programStack.push(commandString.substring(5));
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
}
