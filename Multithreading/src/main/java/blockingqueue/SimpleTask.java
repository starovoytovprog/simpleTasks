package blockingqueue;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Простая выполняемая задача
 *
 * @author starovoytov
 * @created 30.11.2017
 * @$Author$
 * @$Revision$
 */
public class SimpleTask implements ExecutableTask
{
	private static final AtomicInteger checkSum = new AtomicInteger(0);
	private final int id;

	public SimpleTask()
	{
		id = UniqueIdGenerator.nextId();
	}

	public static int getCheckSum()
	{
		return checkSum.get();
	}

	public static void clearCheckSum()
	{
		checkSum.set(0);
	}

	public int getId()
	{
		return id;
	}

	@Override
	public Object execute()
	{
		return checkSum.addAndGet(id);
	}
}
