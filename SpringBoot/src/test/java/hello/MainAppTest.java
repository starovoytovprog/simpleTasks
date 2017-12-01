package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Тест инициализации и запуска Spring Boot {@link MainApp}
 *
 * @author starovoytov
 * @created 01.12.2017
 * @$Author$
 * @$Revision$
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainAppTest
{
	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * Тест страницы Hello World SpringBoot!
	 */
	@Test
	public void helloWorldTest()
	{
		String body = this.restTemplate.getForObject("/", String.class);
		org.junit.Assert.assertEquals("Hello World SpringBoot!", body);
	}
}
