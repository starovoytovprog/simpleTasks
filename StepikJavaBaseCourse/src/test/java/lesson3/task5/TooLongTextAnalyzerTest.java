package lesson3.task5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson3.task5.TooLongTextAnalyzer {@link TooLongTextAnalyzer}
 *
 * @author Starovoytov
 * @since 06.04.2018
 */
public class TooLongTextAnalyzerTest
{
	private static final TextAnalyzer ANALYZER = new TooLongTextAnalyzer(30);

	/**
	 * Проверка длины сообщения
	 */
	@Test
	public void testRun()
	{
		assertEquals(ANALYZER.processText(""), Label.OK);
		assertEquals(ANALYZER.processText("Это не длинное сообщение."), Label.OK);
		assertEquals(ANALYZER.processText("А это длинное. А потому что нельзя упоминать путина в суе!"), Label.TOO_LONG);
	}
}
