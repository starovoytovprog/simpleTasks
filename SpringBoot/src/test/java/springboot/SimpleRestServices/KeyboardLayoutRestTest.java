package springboot.SimpleRestServices;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Тест REST-сервиса KeyboardLayout {@link KeyboardLayoutRest}
 *
 * @author Starovoytov
 * @since 11.12.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KeyboardLayoutRestTest
{
	private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
		MediaType.APPLICATION_JSON.getSubtype(),
		Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup()
	{
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Тестирование конвертации латинских букв в нижнем регистре
	 */
	@Test
	public void LowerLatinLettersTest() throws Exception
	{
		String lowerLatinsAlphavite = "qwertyuiop[]asdfghjkl;'zxcvbnm,./`";
		String validResult = "йцукенгшщзхъфывапролджэячсмитьбю.ё";

		mockMvc.perform(post("/KeyboardLayout")
			.contentType(contentType)
			.content(lowerLatinsAlphavite)).andExpect(content().string(validResult));
	}
}
