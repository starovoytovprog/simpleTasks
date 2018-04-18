package lesson5.task3;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Реализуйте метод, который зачитает данные из InputStream и преобразует их в строку, используя заданную кодировку.
 * Пример
 * InputStream последовательно возвращает четыре байта: 48 49 50 51.
 * Метод, вызванный для такого InputStream и кодировки ASCII, должен вернуть строку "0123".
 *
 * @author Starovoytov
 * @since 12.04.2018
 */
public class StreamHelper {
    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        Reader r = new InputStreamReader(inputStream, charset);
        StringBuilder res = new StringBuilder();
        int intChar;

        while ((intChar = r.read()) != -1) {
            res.append((char) intChar);
        }

        return res.toString();
    }
}
