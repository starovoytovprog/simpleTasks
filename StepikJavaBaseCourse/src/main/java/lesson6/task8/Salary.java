package lesson6.task8;

/**
 * Зарплата
 *
 * @author Starovoytov
 * @since 18.04.2018
 */
public class Salary implements MailServiceAcceptable {
	private final String jobPlace;
	private final String name;
	private final Integer summ;

	public Salary(String jobPlace, String name, int summ) {
		this.jobPlace = jobPlace;
		this.name = name;
		this.summ = summ;
	}

	@Override
	public String getTo() {
		return name;
	}

	@Override
	public Integer getContent() {
		return summ;
	}
}
