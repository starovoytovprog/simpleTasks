package lesson3.task5;

/**
 * Проверка ключевых слов
 *
 * @author Starovoytov
 * @since 06.04.2018
 */
public abstract class KeywordAnalyzer implements TextAnalyzer {
    protected abstract String[] getKeywords();

    protected abstract Label getLabel();

    @Override
    public Label processText(String text) {
        for (String keyWord : getKeywords()) {
            if (text.contains(keyWord)) {
                return getLabel();
            }
        }

        return Label.OK;
    }
}
