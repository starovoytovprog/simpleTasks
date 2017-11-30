package blockingqueue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Генератор уникальных последовательных ID
 *
 * @author starovoytov
 * @created 30.11.2017
 * @$Author$
 * @$Revision$
 */
public class UniqueIdGenerator
{
	private final static AtomicInteger generatorValue = new AtomicInteger(0);

	public static long nextId()
	{
		return generatorValue.incrementAndGet();
	}
}
