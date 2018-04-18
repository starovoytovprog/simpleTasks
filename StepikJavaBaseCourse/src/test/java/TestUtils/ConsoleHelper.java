package TestUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Вспомогательный класс для работы с консолью
 *
 * @author Starovoytov
 * @since 18.04.2018
 */
public class ConsoleHelper {
    public static void testMain(String in, String etalon, Class mainClass) throws Exception {
        InputStream systemInputBuffer = System.in;
        PrintStream systemOutputBuffer = System.out;

        InputStream testStrim = new ByteArrayInputStream(in.getBytes());
        System.setIn(testStrim);

        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        PrintStream testPrint = new PrintStream(resultStream);
        System.setOut(testPrint);

        String[] args = null;
        mainClass.getMethod("main", String[].class).invoke(null, (Object) args);

        System.setIn(systemInputBuffer);
        System.setOut(systemOutputBuffer);

        assertEquals(etalon, resultStream.toString());
    }
}
