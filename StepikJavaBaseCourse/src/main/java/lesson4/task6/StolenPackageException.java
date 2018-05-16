package lesson4.task6;

/**
 * Обнаружена похищенная посылка.
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class StolenPackageException extends RuntimeException {
	public StolenPackageException() {
		super();
	}

	public StolenPackageException(String errorMessage) {
		super(errorMessage);
	}

	public StolenPackageException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
