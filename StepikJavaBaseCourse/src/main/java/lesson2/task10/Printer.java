package lesson2.task10;

import java.util.HashMap;
import java.util.Map;

/**
 * Вам дан список ролей и сценарий пьесы в виде массива строчек.
 * Каждая строчка сценария пьесы дана в следующем виде:
 * Роль: текст
 * Текст может содержать любые символы.
 *
 * Напишите метод, который будет группировать строчки по ролям, пронумеровывать их и возвращать результат в виде готового текста (см. пример). Каждая группа распечатывается в следующем виде:
 * Роль:
 * i) текст
 * j) текст2
 * ...
 * ==перевод строки==
 *
 * Sample Input:

 roles:
 Городничий
 Аммос Федорович
 Артемий Филиппович
 Лука Лукич
 textLines:
 Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.
 Аммос Федорович: Как ревизор?
 Артемий Филиппович: Как ревизор?
 Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.
 Аммос Федорович: Вот те на!
 Артемий Филиппович: Вот не было заботы, так подай!
 Лука Лукич: Господи боже! еще и с секретным предписаньем!

 Sample Output:

 Городничий:
 1) Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.
 4) Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.

 Аммос Федорович:
 2) Как ревизор?
 5) Вот те на!

 Артемий Филиппович:
 3) Как ревизор?
 6) Вот не было заботы, так подай!

 Лука Лукич:
 7) Господи боже! еще и с секретным предписаньем!

 *
 * @author Starovoytov
 * @since 04.04.2018
 */
public class Printer
{
	public String getResult(String[] roles, String[] textLines)
	{
		return printTextPerRole(roles, textLines);
	}

	private String printTextPerRole(String[] roles, String[] textLines)
	{
		Map<String, StringBuilder> resultMap = new HashMap<>();

		for (String role : roles)
		{
			resultMap.put(role, new StringBuilder(role + ":" + System.lineSeparator()));
		}

		for (int i = 0; i < textLines.length; i++)
		{
			StringBuilder currentString = new StringBuilder((i + 1) + ") ");
			currentString.append(textLines[i].substring(textLines[i].indexOf(':') + 2));
			String key = textLines[i].substring(0, textLines[i].indexOf(':'));
			resultMap.replace(key, resultMap.get(key).append(currentString).append(System.lineSeparator()));
		}

		StringBuilder resultString = new StringBuilder();
		for (int i = 0; i < roles.length; i++)
		{
			resultString.append(resultMap.get(roles[i]));
			if (i < roles.length - 1)
			{
				resultString.append(System.lineSeparator());
			}
		}

		System.out.println(resultString);
		return resultString.toString();
	}
}
