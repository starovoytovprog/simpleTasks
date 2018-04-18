package lesson6.task8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование lesson6.task8.Main {@link Main}
 *
 * @author Starovoytov
 * @since 18.04.2018
 */
public class MainTest
{
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun()
	{
		// Random variables
		String randomFrom = "From"; // Некоторая случайная строка. Можете выбрать ее самостоятельно.
		String randomTo = "To";  // Некоторая случайная строка. Можете выбрать ее самостоятельно.
		int randomSalary = 100;  // Некоторое случайное целое положительное число. Можете выбрать его самостоятельно.

		// Создание списка из трех почтовых сообщений.
		MailMessage firstMessage = new MailMessage(
			"Robert Howard",
			"H.P. Lovecraft",
			"This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"
		);

		assertEquals(firstMessage.getFrom(), "Robert Howard");
		assertEquals(firstMessage.getTo(), "H.P. Lovecraft");
		assertTrue(firstMessage.getContent().endsWith("Howard!"));

		MailMessage secondMessage = new MailMessage(
			"Jonathan Nolan",
			"Christopher Nolan",
			"Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
		);

		MailMessage thirdMessage = new MailMessage(
			"Stephen Hawking",
			"Christopher Nolan",
			"Я так и не понял Интерстеллар."
		);

		List<MailMessage> messages = Arrays.asList(
			firstMessage, secondMessage, thirdMessage
		);

		// Создание почтового сервиса.
		MailService<String> mailService = new MailService<>();

		// Обработка списка писем почтовым сервисом
		messages.stream().forEachOrdered(mailService);

		// Получение и проверка словаря "почтового ящика",
		//   где по получателю можно получить список сообщений, которые были ему отправлены
		Map<String, List<String>> mailBox = mailService.getMailBox();

		assertEquals(mailBox.get("H.P. Lovecraft"), Arrays.asList("This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!"));

		assertEquals(mailBox.get("Christopher Nolan")
			, Arrays.asList("Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!"
				, "Я так и не понял Интерстеллар."));

		assertEquals(mailBox.get(randomTo), Collections.<String>emptyList());

		// Создание списка из трех зарплат.
		Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
		Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
		Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

		// Создание почтового сервиса, обрабатывающего зарплаты.
		MailService<Integer> salaryService = new MailService<>();

		// Обработка списка зарплат почтовым сервисом
		Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

		// Получение и проверка словаря "почтового ящика",
		//   где по получателю можно получить список зарплат, которые были ему отправлены.
		Map<String, List<Integer>> salaries = salaryService.getMailBox();
		assertEquals(salaries.get(salary1.getTo()), Arrays.asList(1));
		assertEquals(salaries.get(salary2.getTo()), Arrays.asList(Integer.MAX_VALUE));
		assertEquals(salaries.get(randomTo), Arrays.asList(randomSalary));
	}
}
