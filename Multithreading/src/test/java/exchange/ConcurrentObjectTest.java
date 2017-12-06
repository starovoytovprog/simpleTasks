package exchange;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование ConcurrentObject {@link ConcurrentObject}
 *
 * @author Starovoytov
 * @since 30.11.2017
 */
public class ConcurrentObjectTest
{
	private final CountDownLatch whileLockObject = new CountDownLatch(1);
	private final CountDownLatch whileUnLockObject = new CountDownLatch(1);

	/**
	 * Тестирование блокировки объекта
	 *
	 * @throws InterruptedException Получен сигнал прерывания
	 */
	@Test
	public void concurrentObjectLockTest() throws InterruptedException
	{
		Integer lockedObject = 14;
		ConcurrentObject<Integer> myObject = new ConcurrentObject<>(lockedObject);

		LockAndUnlock newThread = new LockAndUnlock(myObject);
		newThread.start();

		whileLockObject.await();
		assertFalse(myObject.tryLock());

		newThread.interrupt();
		whileUnLockObject.await();
		assertTrue(myObject.tryLock());
		myObject.unLock();
	}

	/**
	 * Класс-поток, блокирующий и разблокирующий объект
	 */
	private class LockAndUnlock extends Thread
	{
		private final ConcurrentObject myObject;

		public LockAndUnlock(ConcurrentObject myObject)
		{
			this.myObject = myObject;
		}

		@Override
		public void run()
		{
			myObject.tryLock();
			whileLockObject.countDown();

			while (!isInterrupted())
			{
				yield();
			}

			myObject.unLock();
			whileUnLockObject.countDown();
		}
	}
}
