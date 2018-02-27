package springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Тест инициализации и запуска Spring Boot {@link MainApp}
 *
 * @author Starovoytov
 * @since 01.12.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainAppTest {
    private static final String HELLO_WORLD_OUT = "Hello World SpringBoot!";
    private static final String HELLO_WORLD_PATH = "/";

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Тест страницы Hello World SpringBoot!
     */
    @Test
    public void helloWorldTest() {
        String body = this.restTemplate.getForObject(HELLO_WORLD_PATH, String.class);
        assertEquals(HELLO_WORLD_OUT, body);
    }
}
