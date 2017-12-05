package exchange;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Блокирующийся объект
 *
 * @author starovoytov
 * @created 30.11.2017
 * @$Author$
 * @$Revision$
 */
public class ConcurrentObject<T>
{
	private T value;
	private final ReentrantLock lock;

	/**
	 * Конструктор блокирующегося объекта
	 *
	 * @param value Значение объекта
	 */
	public ConcurrentObject(T value)
	{
		this.value = value;
		lock = new ReentrantLock();
	}

	/**
	 * Получить значение объекта
	 *
	 * @return значение объекта
	 */
	public T getValue()
	{
		return value;
	}

	/**
	 * Установить новое значение объекта
	 *
	 * @param value новое значение
	 */
	public void setValue(T value)
	{
		this.value = value;
	}

	/**
	 * Попытка заблокировать объект
	 *
	 * @return true, если блокировка была успешной
	 */
	public boolean tryLock()
	{
		return lock.tryLock();
	}

	/**
	 * Разблокировка объекта
	 */
	public void unLock()
	{
		lock.unlock();
	}
}
