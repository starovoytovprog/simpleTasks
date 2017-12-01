package blockingqueue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование SimpleTask {@link SimpleTask}
 *
 * @author starovoytov
 * @created 30.11.2017
 * @$Author$
 * @$Revision$
 */
public class SimpleTaskTest
{
	@Test
	public void simpleTaskConstructorTest()
	{
		int startValue = UniqueIdGenerator.nextId() + 1;
		for (int i = startValue; i <= startValue + 10; i++)
		{
			SimpleTask task = new SimpleTask();
			assertEquals(task.getId(), i);
		}
	}

	@Test
	public void simpleTaskExecuteTest()
	{
		SimpleTask.clearCheckSum();
		int checkSum = 0;
		int startValue = UniqueIdGenerator.nextId() + 1;
		for (long i = startValue; i <= startValue + 10; i++)
		{
			new SimpleTask().execute();
			checkSum += i;
		}

		assertEquals(SimpleTask.getCheckSum(), checkSum);
	}
}
