package lesson3.task2;

/**
 * Класс для примера
 *
 * @author starovoytov
 * @since 2019.02.01
 */
public class CallCounter {
	private int count = 0;

	public synchronized void increment(){
		count++;
	}

	public int getCount() {
		return count;
	}
}
