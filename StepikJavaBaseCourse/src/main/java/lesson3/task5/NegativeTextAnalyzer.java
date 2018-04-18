package lesson3.task5;

/**
 * Фильтр негативных комментариев (реализация @Link {@link TextAnalyzer})
 *
 * @author Starovoytov
 * @since 06.04.2018
 */
public class NegativeTextAnalyzer extends KeywordAnalyzer {
    private final String[] keywords = {":(", "=(", ":|"};

    @Override
    protected String[] getKeywords() {
        return keywords;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}
