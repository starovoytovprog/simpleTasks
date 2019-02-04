package lesson3.task1;

/**
 * Разбор примера
 *
 * @author starovoytov
 * @since 2019.02.01
 */
public class Main {
	private static final int THREADS_NUMBER = 100;

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < THREADS_NUMBER; ++i) {
			//Thread thread = new RandomSequenceExample();
			Thread thread = new SerialSequenceExample(i);
			//System.out.println("Start: " + thread.getName());
			thread.start();
		}
	}

	private static class RandomSequenceExample extends Thread {
		public void run() {
			System.out.println("Run: " + this.getName());
		}
	}

	@SuppressWarnings("UnusedDeclaration")
	private static class SerialSequenceExample extends Thread {
		private static int currentMax = 1;
		private int mainId;
		private final static Object waitObject = new Object();

		public SerialSequenceExample(int id) {
			this.mainId = id;
		}

		public void run() {
			try {
				System.out.println("Run: " + mainId);
				synchronized (waitObject) {
					while (mainId > currentMax) {
						waitObject.wait();
						System.out.println("Awakened: " + mainId);
					}

					currentMax++;
					System.out.println("Finished: " + mainId);
					waitObject.notifyAll();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
