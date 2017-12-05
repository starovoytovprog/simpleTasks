package blockingqueue;

/**
 * Базовая фабрика создания ExecutableTask-экземпляров
 *
 * @author starovoytov
 * @created 04.12.2017
 * @$Author$
 * @$Revision$
 */
public interface ExecutableTaskCreator
{
	/**
	 * Создать новую задачу
	 *
	 * @return Новая задача
	 */
	ExecutableTask create();
}
