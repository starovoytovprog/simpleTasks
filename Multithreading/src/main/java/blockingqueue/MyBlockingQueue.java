package blockingqueue;

import java.util.LinkedList;

/**
 * Реализация блокирующей очереди
 *
 * @author starovoytov
 * @created 01.12.2017
 * @$Author$
 * @$Revision$
 */
public class MyBlockingQueue
{
	private final LinkedList<ExecutableTask> taskCollection;

	public MyBlockingQueue()
	{
		taskCollection = new LinkedList<>();
	}

	/**
	 * Возвращает количество задач в очереди
	 *
	 * @return Количество задач в очереди
	 */
	public int getSize()
	{
		return taskCollection.size();
	}

	/**
	 * Добавить задачу в очередь
	 *
	 * @param newTask Новая задача
	 */
	public void put(ExecutableTask newTask)
	{
		synchronized (taskCollection)
		{
			taskCollection.add(newTask);
		}
	}

	/**
	 * Забрать задачу из очереди
	 *
	 * @return Забранная задача
	 */
	public ExecutableTask get()
	{
		ExecutableTask t;
		synchronized (taskCollection)
		{
			t = taskCollection.pollFirst();
		}

		return t;
	}
}
