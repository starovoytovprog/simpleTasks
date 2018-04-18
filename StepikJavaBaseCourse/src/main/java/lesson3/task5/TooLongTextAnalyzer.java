package lesson3.task5;

/**
 * Фильтр длинных комментариев (реализация @Link {@link TextAnalyzer})
 *
 * @author Starovoytov
 * @since 06.04.2018
 */
public class TooLongTextAnalyzer implements TextAnalyzer {
    private final int maxLength;

    public TooLongTextAnalyzer(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Label processText(String text) {
        if (text.length() > maxLength) {
            return Label.TOO_LONG;
        }

        return Label.OK;
    }
}
