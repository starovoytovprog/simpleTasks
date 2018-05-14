package lesson3.task5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson3.task5.NegativeTextAnalyzer {@link NegativeTextAnalyzer}
 *
 * @author Starovoytov
 * @since 06.04.2018
 */
public class NegativeTextAnalyzerTest {
	private static final TextAnalyzer ANALYZER = new NegativeTextAnalyzer();

	/**
	 * Проверка поиска спама
	 */
	@Test
	public void testRun() {
		assertEquals(ANALYZER.processText(""), Label.OK);
		assertEquals(ANALYZER.processText("Здесь нет негатива."), Label.OK);
		assertEquals(ANALYZER.processText("А здесь есть:( А потому что нельзя упоминать путина в суе!"), Label.NEGATIVE_TEXT);
	}
}
