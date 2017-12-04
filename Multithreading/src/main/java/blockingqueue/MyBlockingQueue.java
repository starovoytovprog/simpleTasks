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

	public int getSize()
	{
		return taskCollection.size();
	}

	public void put(ExecutableTask newTask)
	{
		synchronized (taskCollection)
		{
			taskCollection.add(newTask);
		}
	}

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
