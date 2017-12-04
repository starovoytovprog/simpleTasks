package blockingqueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.validateMockitoUsage;

/**
 * Тестирование ProducerPool {@link ProducerPool}
 *
 * @author starovoytov
 * @created 01.12.2017
 * @$Author$
 * @$Revision$
 */
public class ProducerPoolTest
{
	private MockTaskCreator mockTaskCreator;

	@Before
	public void init()
	{
		mockTaskCreator = new MockTaskCreator();
	}

	@Test
	public void producerPoolOneThreadTest() throws InterruptedException
	{
		poolTest(1, 10);
	}

	@Test
	public void producerPoolMultiThreadTest() throws InterruptedException
	{
		poolTest(30, 100);
	}

	private void poolTest(int threadCount, int countTaskForThread) throws InterruptedException
	{
		MyBlockingQueue queue = new MyBlockingQueue();
		ProducerPool pool = new ProducerPool(queue, threadCount, countTaskForThread, mockTaskCreator);
		pool.start();
		pool.getLatch().await();
		assertEquals(threadCount * countTaskForThread, queue.getSize());
	}

	@After
	public void validate()
	{
		validateMockitoUsage();
	}
}
