package lesson5.task1;

import java.io.IOException;
import java.io.InputStream;

/**
 * Напишите метод, читающий входной поток и вычисляющий контрольную сумму прочитанных данных.
 * Контрольная сумма данных вычисляется по следующему алгоритму:
 * 1) Контрольная сумма представляет собой число типа int. Контрольная сумма пустого набора данных равна нулю.
 * 2) Контрольная сумма непустого набора данных вычисляется по следующей рекуррентной формуле: Cn+1=rotateLeft(Cn) xor bn+1 , где Cn — контрольная сумма первых n байт данных, rotateLeft — циклический сдвиг бит числа на один бит влево (чтобы не изобретать велосипед, используйте Integer.rotateLeft), bn — n-ный байт данных.
 * Поскольку метод не открывал данный InputStream, то и закрывать его он не должен. Выброшенное из методов InputStream исключение должно выбрасываться из метода.
 * Пример:
 * На вход подан InputStream, последовательно возвращающий три байта: 0x33 0x45 0x01. В качестве контрольной суммы должно быть возвращено число 71.
 *
 * @author Starovoytov
 * @since 12.04.2018
 */
public class CheckSumHelper {
	public static int checkSumOfStream(InputStream inputStream) throws IOException {
		int checkSum = 0;
		int buf;
		while ((buf = inputStream.read()) != -1) {
			checkSum = Integer.rotateLeft(checkSum, 1) ^ buf;
		}

		return checkSum;
	}
}
