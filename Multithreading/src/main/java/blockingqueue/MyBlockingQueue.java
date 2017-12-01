package blockingqueue;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

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
	private final ReentrantLock operationLocker;

	public MyBlockingQueue()
	{
		taskCollection = new LinkedList<>();
		operationLocker = new ReentrantLock();
	}

	public int getSize()
	{
		return taskCollection.size();
	}

	public void put(ExecutableTask newTask)
	{
		operationLocker.lock();
		taskCollection.add(newTask);
		operationLocker.unlock();
	}

	public ExecutableTask get()
	{
		operationLocker.lock();
		ExecutableTask t = taskCollection.pollFirst();
		operationLocker.unlock();
		return t;
	}
}
