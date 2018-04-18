package lesson3.task5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование lesson3.task5.Checker {@link Checker}
 *
 * @author Starovoytov
 * @since 06.04.2018
 */
public class CheckerTest {
    private static final Checker CHECKER = new Checker();

    /**
     * Проверка результата выполнения метода
     */
    @Test
    public void testRun() {
        String[] spamWords = {"путин", "медведев", "песков"};
        TextAnalyzer[] analyzers = {new TooLongTextAnalyzer(35), new NegativeTextAnalyzer(), new SpamAnalyzer(spamWords)};

        String text1 = "Здесь всё ок";
        String text2 = "А здесь вспомнили путина.";
        String text3 = "Тут очень много болтали не по теме. Флуд в общем.";
        String text4 = "Всё заполонили флудеры :(";

        assertEquals(CHECKER.checkLabels(analyzers, text1), Label.OK);
        assertEquals(CHECKER.checkLabels(analyzers, text2), Label.SPAM);
        assertEquals(CHECKER.checkLabels(analyzers, text3), Label.TOO_LONG);
        assertEquals(CHECKER.checkLabels(analyzers, text4), Label.NEGATIVE_TEXT);
    }
}
