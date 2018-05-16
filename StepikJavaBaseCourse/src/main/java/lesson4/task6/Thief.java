package lesson4.task6;

/**
 * Вор, который ворует самые ценные посылки и игнорирует все остальное. Вор принимает в конструкторе переменную int – минимальную стоимость посылки, которую он будет воровать. Также, в данном классе должен присутствовать метод getStolenValue, который возвращает суммарную стоимость всех посылок, которые он своровал. Воровство происходит следующим образом: вместо посылки, которая пришла вору, он отдает новую, такую же, только с нулевой ценностью и содержимым посылки "stones instead of {content}".
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class Thief implements MailService {
	private final int minCost;
	private int stolenValue;

	public Thief(int minCost) {
		this.minCost = minCost;
	}

	public int getStolenValue() {
		return stolenValue;
	}

	@Override
	public Sendable processMail(Sendable mail) {
		if (mail instanceof MailPackage) {
			if (((MailPackage) mail).getContent().getPrice() >= minCost) {
				stolenValue += ((MailPackage) mail).getContent().getPrice();
				mail = new MailPackage(mail.getFrom(), mail.getTo(), new Package("stones instead of " + ((MailPackage) mail).getContent().getContent(), 0));
			}
		}

		return mail;
	}
}
