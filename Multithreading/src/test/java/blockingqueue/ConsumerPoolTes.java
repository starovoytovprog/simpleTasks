package blockingqueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Тестирование ConsumerPool {@link ConsumerPool}
 *
 * @author starovoytov
 * @created 01.12.2017
 * @$Author$
 * @$Revision$
 */
public class ConsumerPoolTes
{
	private static final int EMPTY = 0;
	private ExecutableTask mockTask;

	@Before
	public void init()
	{
		mockTask = mock(ExecutableTask.class);
	}

	@Test
	public void consumerPoolOneThreadTest() throws InterruptedException
	{
		testConsumer(1, 10000);
	}

	@Test
	public void consumerPoolMultiThreadTest() throws InterruptedException
	{
		testConsumer(10, 10000);
	}

	private void testConsumer(int threadCount, int queueSize) throws InterruptedException
	{
		MyBlockingQueue queue = new MyBlockingQueue();
		ConsumerPool pool = new ConsumerPool(queue, threadCount);

		for (int i = 0; i < queueSize; i++)
		{
			queue.put(mockTask);
		}

		pool.start();
		pool.stop();
		pool.getLatch().await();
		assertEquals(EMPTY, queue.getSize());
		verify(mockTask, times(queueSize)).execute();
	}

	@After
	public void validate()
	{
		validateMockitoUsage();
	}
}
