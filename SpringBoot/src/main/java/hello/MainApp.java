package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Инициализация и запуск Spring Boot
 *
 * @author Starovoytov
 * @since 01.12.2017
 */
@Controller
@SpringBootApplication
public class MainApp
{
	/**
	 * Точка входа
	 * @param args можно переопределить параметры SpringBoot передав их в параметрах args, например: --server.port=8090
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(MainApp.class, args);
	}

	/**
	 * @return Домашняя страница http://localhost
	 */
	@RequestMapping("/")
	@ResponseBody
	String home()
	{
		return "Hello World SpringBoot!";
	}
}
