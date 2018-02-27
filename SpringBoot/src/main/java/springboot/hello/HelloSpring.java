package springboot.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HelloWorld контроллер
 *
 * @author Starovoytov
 * @since 11.12.2017
 */
@Controller
public class HelloSpring {
    /**
     * @return Домашняя страница http://localhost
     */
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World SpringBoot!";
    }
}
