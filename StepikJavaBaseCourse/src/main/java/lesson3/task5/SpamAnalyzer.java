package lesson3.task5;

/**
 * Спам-фильтр (реализация @Link {@link TextAnalyzer})
 *
 * @author Starovoytov
 * @since 06.04.2018
 */
public class SpamAnalyzer extends KeywordAnalyzer {
    private final String[] keywords;

    public SpamAnalyzer(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    protected String[] getKeywords() {
        return keywords;
    }

    @Override
    protected Label getLabel() {
        return Label.SPAM;
    }
}
