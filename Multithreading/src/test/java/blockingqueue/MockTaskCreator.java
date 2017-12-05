package blockingqueue;

import static org.mockito.Mockito.mock;

/**
 * Реализация фабрики создания ExecutableTask-экземпляров, создающая mock-заглушку
 *
 * @author Starovoytov
 * @since 04.12.2017
 */
public class MockTaskCreator implements ExecutableTaskCreator
{
	private ExecutableTask mockTask;

	public MockTaskCreator()
	{
		mockTask = mock(ExecutableTask.class);
	}

	@Override
	public ExecutableTask create()
	{
		return mockTask;
	}
}
