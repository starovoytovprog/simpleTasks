package lesson4.task6;

/**
 * Класс, моделирующий ненадежного работника почты, который вместо того, чтобы передать почтовый объект непосредственно в сервис почты, последовательно передает этот объект набору третьих лиц, а затем, в конце концов, передает получившийся объект непосредственно экземпляру RealMailService. У UntrustworthyMailWorker должен быть конструктор от массива MailService ( результат вызова processMail первого элемента массива передается на вход processMail второго элемента, и т. д.) и метод getRealMailService, который возвращает ссылку на внутренний экземпляр RealMailService.
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class UntrustworthyMailWorker implements MailService
{
	private final RealMailService realMailService;
	private final MailService[] mailServices;

	public UntrustworthyMailWorker(MailService[] mailServices)
	{
		this.mailServices = mailServices;
		realMailService = new RealMailService();
	}

	@Override
	public Sendable processMail(Sendable mail)
	{
		for (MailService mailService : mailServices)
		{
			mail = mailService.processMail(mail);
		}

		return getRealMailService().processMail(mail);
	}

	public RealMailService getRealMailService()
	{
		return realMailService;
	}
}
