package lesson4.task6;

/**
 * Интерфейс: сущность, которую можно отправить по почте.
 * У такой сущности можно получить от кого и кому направляется письмо.
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public interface Sendable
{
	String getFrom();

	String getTo();
}
