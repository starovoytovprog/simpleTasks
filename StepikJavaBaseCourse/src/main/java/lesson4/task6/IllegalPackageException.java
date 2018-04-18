package lesson4.task6;

/**
 * Обнаружена посылка с запрещённым содержимым.
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class IllegalPackageException extends RuntimeException {
    public IllegalPackageException() {
        super();
    }

    public IllegalPackageException(String errorMessage) {
        super(errorMessage);
    }

    public IllegalPackageException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
