package mainpack;

/**
 * Запуск приложения
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class Main {
	static int[] sum = {280, 290, 180, 455, 215, 119, 86, 84, 1440, 873, 237, 165, 34};
	static int[] count = {3, 20, 100, 10, 20, 20, 20, 20, 5, 5, 10, 27, 1};
	static int res = 12454;

	/**
	 * Вывод "Hello World!" в консоль
	 *
	 * @param args Не используется
	 */
	public static void main(String[] args) {
		System.out.println("Hello World!");

		int[] arr = new int[sum.length];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = sum[i]*count[i];
		}

		/*for (int k = 0; k < sum.length; k++) {
			for (int j = 0; j < count[k]; j++) {
				for (int i = 0; i < arr.length; i++) {
					arr[i] = sum[i]*(i == k ? j : count[i]);
				}

				String s = getAllSumm(arr, res);
				if (s != null) {
					System.out.println("k=" + k + ", j = " + j + ", s = " + s);
				}
			}
		}*/

		System.out.println(getAllSumm(arr, res));
	}

	private static String getAllSumm(int[] arr_t, int sum) {
		int[] arr = new int[arr_t.length];

		for (int i = 0; i < arr_t.length; i++) {
			arr[i] = arr_t[i];
		}

		int max_count = (int) Math.pow(2, arr.length);

		for (int count = 1; count < max_count; count++) {
			if (getCurrentSumWithoutMask(arr, count, sum) == sum) {
				return getArrFromMask(arr, count);
			}
		}

		return null;
	}

	private static String getArrFromMask(int[] arr, int maskCount) {
		String sum = "[";

		for (int i = 0; i < arr.length; i++) {
			if (maskCount%2 == 1) {
				sum += arr[i] + "/" + i + ",";
			}

			if (maskCount == 0) {
				return sum + "]";
			}

			maskCount = maskCount>>1;
		}

		sum += "]";
		return sum;
	}

	private static int getCurrentSumWithoutMask(int[] arr, int maskCount, int current_sum) {

		int sum = 0;

		for (int i = 0; i < arr.length; i++) {
			if (maskCount%2 == 1) {
				sum += arr[i];
			}

			if (sum > current_sum) {
				return -1;
			}

			maskCount = maskCount>>1;

			if (maskCount == 0) {
				return sum;
			}
		}

		return sum;
	}
}
