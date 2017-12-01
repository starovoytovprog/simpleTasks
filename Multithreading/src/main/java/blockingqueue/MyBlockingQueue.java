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
	private final LinkedList<SimpleTask> taskCollection;

	public MyBlockingQueue()
	{
		taskCollection = new LinkedList<>();
	}

	public int getSize()
	{
		return taskCollection.size();
	}

	public void put(SimpleTask newTask)
	{
		taskCollection.add(newTask);
	}
}
