package blockingqueue;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Тестирование MyBlockingQueue {@link MyBlockingQueue}
 *
 * @author starovoytov
 * @created 30.11.2017
 * @$Author$
 * @$Revision$
 */
public class MyBlockingQueueTest
{
	@Test
	public void myBlockingQueuePutTest()
	{
		MyBlockingQueue queue = new MyBlockingQueue();
		assertEquals(queue.getSize(), 0);

		for (int i = 1; i <= 10; i++)
		{
			queue.put(new SimpleTask());
			assertEquals(queue.getSize(), i);
		}
	}

	@Test
	public void myBlockingQueueGetTest()
	{
		MyBlockingQueue queue = new MyBlockingQueue();

		int count = 10;

		for (int i = 0; i < count; i++)
		{
			queue.put(new SimpleTask());
		}

		for (int i = 0; i < count; i++)
		{
			queue.get();
		}

		assertEquals(queue.getSize(), 0);
	}
}
