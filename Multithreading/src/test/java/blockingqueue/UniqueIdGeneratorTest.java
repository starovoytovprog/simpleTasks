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
		for (long i = 1; i <= 10; i++)
		{
			assertEquals(UniqueIdGenerator.nextId(), i);
		}
	}
}
