package rand;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Класс для копирования файлов
 *
 * @author Starovoytov
 * @since 24.09.2018
 */
public class Randomizer {

	/**
	 * Копирование файлов
	 *
	 * @param source      директория с копируемыми файлами
	 * @param destination директория назначения
	 * @throws IOException исключения ввода-вывода при обращении к файлам
	 */
	public static void CopyAll(String source, String destination) throws IOException {
		clearDestinationPath(destination);

		List<File> files = Arrays.asList(new File(source).listFiles());
		Collections.shuffle(files);

		for (File file : files) {
			Files.copy(file.toPath(), new File(destination + "\\" + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Copied file:  " + file);
		}
	}

	/**
	 * Очистка каталога
	 *
	 * @param destination каталог
	 * @throws IOException исключения ввода-вывода при обращении к файлам
	 */
	private static void clearDestinationPath(String destination) throws IOException {
		Files.walkFileTree(Paths.get(destination), (new DeleteVisitor()));
	}

	/**
	 * Класс для очистки каталога
	 */
	private static class DeleteVisitor extends SimpleFileVisitor<Path> {
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			Files.delete(file);
			System.out.println("Delete file: " + file);
			return FileVisitResult.CONTINUE;
		}
	}
}
