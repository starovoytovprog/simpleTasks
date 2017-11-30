package exchange;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Блокирующийся объект
 *
 * @author starovoytov
 * @created 29.11.2017
 * @$Author$
 * @$Revision$
 */
public class ConcurrentObject<T>
{
	private T value;
	private ReentrantLock lock;

	public ConcurrentObject(T value)
	{
		this.value = value;
		lock = new ReentrantLock();
	}

	public T getValue()
	{
		return value;
	}

	public void setValue(T value)
	{
		this.value = value;
	}

	public boolean tryLock()
	{
		return lock.tryLock();
	}

	public void unLock()
	{
		lock.unlock();
	}
}
