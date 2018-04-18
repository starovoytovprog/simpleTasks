package lesson6.task7;

import TestUtils.ConsoleHelper;
import org.junit.Test;

/**
 * Тестирование lesson6.task7.Main {@link Main}
 *
 * @author Starovoytov
 * @since 18.04.2018
 */
public class MainTest {
    /**
     * Проверка результата выполнения метода
     *
     * @throws Exception может бросить исключения
     */
    @Test
    public void testRun() throws Exception {
        ConsoleHelper.testMain("Мама мыла-мыла-мыла раму!"
                , "мыла" + System.lineSeparator() + "мама" + System.lineSeparator() + "раму" + System.lineSeparator()
                , Main.class);
        ConsoleHelper.testMain("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim."
                , "consectetur" + System.lineSeparator() +
                        "faucibus" + System.lineSeparator() +
                        "ipsum" + System.lineSeparator() +
                        "lorem" + System.lineSeparator() +
                        "adipiscing" + System.lineSeparator() +
                        "amet" + System.lineSeparator() +
                        "dolor" + System.lineSeparator() +
                        "eget" + System.lineSeparator() +
                        "elit" + System.lineSeparator() +
                        "mi" + System.lineSeparator()
                , Main.class);
    }
}
