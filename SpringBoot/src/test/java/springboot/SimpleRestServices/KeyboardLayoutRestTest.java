package springboot.SimpleRestServices;

import org.junit.After;
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

import static org.mockito.Mockito.validateMockitoUsage;
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
		MediaType.TEXT_PLAIN.getSubtype(),
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
	 *
	 * @throws Exception исключения от мок-компонента
	 */
	@Test
	public void lowerLatinLettersTest() throws Exception
	{
		String to = "qwertyuiop[]asdfghjkl;'zxcvbnm,.";
		String from = "йцукенгшщзхъфывапролджэячсмитьбю";

		sendTest(to, from);
	}

	/**
	 * Тестирование конвертации повторяющихся букв
	 *
	 * @throws Exception исключения от мок-компонента
	 */
	@Test
	public void repeatLettersTest() throws Exception
	{
		String to = "qqasasasasa";
		String from = "ййфыфыфыфыф";

		sendTest(to, from);
	}

	/**
	 * Тестирование конвертации латинских букв в верхнем регистре
	 *
	 * @throws Exception исключения от мок-компонента
	 */
	@Test
	public void upperLatinLettersTest() throws Exception
	{
		String to = "QWERTYUIOP{}ASDFGHJKL:\"ZXCVBNM<>";
		String from = "ЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";

		sendTest(to, from);
	}

	/**
	 * Тестирование конвертации кириллических букв в нижнем регистре
	 *
	 * @throws Exception исключения от мок-компонента
	 */
	@Test
	public void lowerKirillicLettersTest() throws Exception
	{
		String to = "йцукенгшщзфывапролдячсмить";
		String from = "qwertyuiopasdfghjklzxcvbnm";

		sendTest(to, from);
	}

	/**
	 * Тестирование конвертации кириллических букв в верхнем регистре
	 *
	 * @throws Exception исключения от мок-компонента
	 */
	@Test
	public void upperKirillicLettersTest() throws Exception
	{
		String to = "ЙЦУКЕНГШЩЗФЫВАПРОЛДЯЧСМИТЬ";
		String from = "QWERTYUIOPASDFGHJKLZXCVBNM";

		sendTest(to, from);
	}

	/**
	 * Тестирование конвертации кириллических букв в верхнем регистре
	 *
	 * @throws Exception исключения от мок-компонента
	 */
	@Test
	public void kirillicSentenceTest() throws Exception
	{
		String to = "C]tim to` 'nb[ vzurb[ ahfywepcrb[ ,ekjr? lf dsgtq xf./";
		String from = "Съешь ещё этих мягких французских булок, да выпей чаю.";

		sendTest(to, from);
	}

	private void sendTest(String to, String from) throws Exception
	{
		mockMvc.perform(post("/KeyboardLayout")
			.contentType(contentType)
			.content(to)).andExpect(content().string(from));
	}

	@After
	public void validate()
	{
		validateMockitoUsage();
	}
}
