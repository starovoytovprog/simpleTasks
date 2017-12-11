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
public class KeyboardLayoutRest
{
	@RequestMapping("/KeyboardLayout")
	public String simple(@RequestBody String body)
	{
		return "йцукенгшщзхъфывапролджэячсмитьбю.ё";
	}
}
