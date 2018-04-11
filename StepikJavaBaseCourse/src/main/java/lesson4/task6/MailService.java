package lesson4.task6;

/**
 * Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public interface MailService
{
	Sendable processMail(Sendable mail);
}
