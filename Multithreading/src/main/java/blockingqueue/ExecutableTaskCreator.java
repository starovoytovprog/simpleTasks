package blockingqueue;

/**
 * Базовая фабрика создания ExecutableTask-экземпляров
 *
 * @author Starovoytov
 * @since 04.12.2017
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
