package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Инициализация и запуск Spring Boot
 *
 * @author Starovoytov
 * @since 01.12.2017
 */
@SpringBootApplication
public class MainApp {
    /**
     * Точка входа
     *
     * @param args можно переопределить параметры SpringBoot передав их в параметрах args, например: --server.port=8090
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
