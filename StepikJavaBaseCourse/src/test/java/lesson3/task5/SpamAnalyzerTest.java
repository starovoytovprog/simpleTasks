package lesson3.task5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson3.task5.SpamAnalyzer {@link SpamAnalyzer}
 *
 * @author Starovoytov
 * @since 06.04.2018
 */
public class SpamAnalyzerTest {
	private static final String[] SPAM_WORDS = {"путин", "медведев", "песков"};
	private static final TextAnalyzer ANALYZER = new SpamAnalyzer(SPAM_WORDS);

	/**
	 * Проверка поиска спама
	 */
	@Test
	public void testRun() {
		assertEquals(ANALYZER.processText(""), Label.OK);
		assertEquals(ANALYZER.processText("Здесь нет никаких спам-слов."), Label.OK);
		assertEquals(ANALYZER.processText("А здесь есть, путина нельзя поминать в суе."), Label.SPAM);
	}
}
