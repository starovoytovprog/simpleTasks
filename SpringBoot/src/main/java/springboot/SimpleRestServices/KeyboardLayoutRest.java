package springboot.SimpleRestServices;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Простой REST-сервис, меняет раскладку сообщения
 *
 * @author Starovoytov
 * @since 11.12.2017
 */
@RestController
public class KeyboardLayoutRest {

    private static final String fromlatinAlphabet = "qwertyuiop[]asdfghjkl;'zxcvbnm,.QWERTYUIOP{}ASDFGHJKL:\"ZXCVBNM<>`~/?!@#$%^&*()";
    private static final String tolatinAlphabet = "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮёЁ.,!\"№;%:?*()";

    private static final String fromKirillicAlphabet = "йцукенгшщзфывапролдячсмитьЙЦУКЕНГШЩЗФЫВАПРОЛДЯЧСМИТЬёЁ.,!\"№;%:?*()";
    private static final String toKirillicAlphabet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM`~/?!@#$%^&*()";

    /**
     * Обработка запроса
     *
     * @param body тело запроса, содержит фразу для обработки
     * @return обработанная фраза
     */
    @RequestMapping("/KeyboardLayout")
    public String simple(@RequestBody String body) {

        if (isLatin(body)) {
            body = replaceAll(body, fromlatinAlphabet, tolatinAlphabet);
        } else {
            body = replaceAll(body, fromKirillicAlphabet, toKirillicAlphabet);
        }

        return body;
    }

    private boolean isLatin(String body) {
        char[] chars = body.toCharArray();

        for (char ch : chars) {
            if (fromlatinAlphabet.indexOf(ch) > -1) {
                return true;
            }
        }

        return false;
    }

    private String replaceAll(String content, String a1, String a2) {
        for (int i = 0; i < a1.length() && i < a2.length(); i++) {
            content = replaceChar(i, content, a1, a2);
        }

        return content;
    }

    private String replaceChar(int i, String content, String a1, String a2) {
        return content.replace(a1.charAt(i), a2.charAt(i));
    }
}
