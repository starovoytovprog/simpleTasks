package blockingqueue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование UniqueIdGenerator {@link UniqueIdGenerator}
 *
 * @author starovoytov
 * @created 30.11.2017
 * @$Author$
 * @$Revision$
 */
public class UniqueIdGeneratorTest
{
	@Test
	public void UniqueIdGeneratorNextIdTest()
	{
		int startValue = UniqueIdGenerator.nextId() + 1;
		for (long i = startValue; i <= startValue + 10; i++)
		{
			assertEquals(UniqueIdGenerator.nextId(), i);
		}
	}
}
