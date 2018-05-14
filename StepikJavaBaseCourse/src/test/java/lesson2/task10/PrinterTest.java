package lesson2.task10;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson2.task10.Printer {@link Printer}
 *
 * @author Starovoytov
 * @since 04.04.2018
 */
public class PrinterTest {
	/**
	 * Проверка результата выполнения метода
	 */
	@Test
	public void testRun() {
		String[] roles = {"Городничий", "Аммос Федорович", "Артемий Филиппович", "Лука Лукич"};
		String[] textLines = {"Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.", "Аммос Федорович: Как ревизор?", "Артемий Филиппович: Как ревизор?", "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.", "Аммос Федорович: Вот те на!", "Артемий Филиппович: Вот не было заботы, так подай!", "Лука Лукич: Господи боже! еще и с секретным предписаньем!",};

		String resultText = "Городничий:" + System.lineSeparator() + "1) Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор." + System.lineSeparator() + "4) Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем." + System.lineSeparator() + "" + System.lineSeparator() + "Аммос Федорович:" + System.lineSeparator() + "2) Как ревизор?" + System.lineSeparator() + "5) Вот те на!" + System.lineSeparator() + "" + System.lineSeparator() + "Артемий Филиппович:" + System.lineSeparator() + "3) Как ревизор?" + System.lineSeparator() + "6) Вот не было заботы, так подай!" + System.lineSeparator() + "" + System.lineSeparator() + "Лука Лукич:" + System.lineSeparator() + "7) Господи боже! еще и с секретным предписаньем!" + System.lineSeparator();

		Printer p = new Printer();
		assertEquals(p.getResult(roles, textLines), resultText);
	}
}
