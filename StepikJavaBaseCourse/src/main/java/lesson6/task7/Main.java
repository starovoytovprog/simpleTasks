package lesson6.task7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Напишите программу, читающую из System.in текст в кодировке UTF-8, подсчитывающую в нем частоту появления слов, и в конце выводящую 10 наиболее часто встречающихся слов.
 * Словом будем считать любую непрерывную последовательность символов, состоящую только из букв и цифр. Например, в строке "Мама мыла раму 33 раза!" ровно пять слов: "Мама", "мыла", "раму", "33" и "раза".
 * Подсчет слов должен выполняться без учета регистра, т.е. "МАМА", "мама" и "Мама" — это одно и то же слово. Выводите слова в нижнем регистре.
 * Если в тексте меньше 10 уникальных слов, то выводите сколько есть.
 * Если в тексте некоторые слова имеют одинаковую частоту, т.е. их нельзя однозначно упорядочить только по частоте, то дополнительно упорядочите слова с одинаковой частотой в лексикографическом порядке.
 * Задача имеет красивое решение через стримы без циклов и условных операторов. Попробуйте придумать его.
 * <p>
 * Sample Input 1: Мама мыла-мыла-мыла раму! Sample Output 1: мыла мама раму
 *
 * @author Starovoytov
 * @since 18.04.2018
 */
public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> wordsCount = new HashMap<>();

		br.lines().flatMap(line -> Arrays.stream(line.toLowerCase().replaceAll("[^a-zа-я0-9]", " ").trim().split(" "))).forEach(word -> {
			if (!word.isEmpty()) {
				if (!wordsCount.containsKey(word)) {
					wordsCount.put(word, new Integer("1"));
				}
				else {
					wordsCount.put(word, wordsCount.get(word) + 1);
				}
			}
		});

		wordsCount.keySet().stream().sorted((s1, s2) -> {
			int res = Integer.compare(wordsCount.get(s2), wordsCount.get(s1));

			if (res == 0) {
				res = s1.compareTo(s2);
			}

			return res;
		}).limit(10).forEach(word -> System.out.println(word));
	}
}
