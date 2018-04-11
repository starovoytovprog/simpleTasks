package lesson4.task5;

import java.util.logging.*;

/**
 * В этой задаче вам нужно реализовать метод, настраивающий параметры логирования. Конфигурирование в коде позволяет выполнить более тонкую и хитрую настройку, чем при помощи properties-файла.
 * Требуется выставить такие настройки, чтобы:
 * 1) Логгер с именем "org.stepic.java.logging.ClassA" принимал сообщения всех уровней.
 * 2) Логгер с именем "org.stepic.java.logging.ClassB" принимал только сообщения уровня WARNING и серьезнее.
 * 3) Все сообщения, пришедшие от нижестоящих логгеров на уровень "org.stepic.java", независимо от серьезности сообщения печатались в консоль в формате XML (*) и не передавались вышестоящим обработчикам на уровнях "org.stepic", "org" и "".
 * Не упомянутые здесь настройки изменяться не должны.
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class LoggerConfig
{
	private static void configureLogging()
	{
		Logger logger1 = Logger.getLogger("org.stepic.java.logging.ClassA");
		logger1.setLevel(Level.ALL);

		Logger logger2 = Logger.getLogger("org.stepic.java.logging.ClassB");
		logger2.setLevel(Level.WARNING);

		Logger logger3 = Logger.getLogger("org.stepic.java");
		logger3.setUseParentHandlers(false);

		Handler handler = new ConsoleHandler();
		handler.setLevel(Level.ALL);
		logger3.addHandler(handler);

		Formatter formatter = new XMLFormatter();
		handler.setFormatter(formatter);
	}
}
