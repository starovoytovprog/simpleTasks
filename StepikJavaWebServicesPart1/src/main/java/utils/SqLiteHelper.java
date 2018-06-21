package utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Набор инструментов для работы с базами SqLite
 *
 * @author Starovoytov
 * @since 14.05.2018
 */
public class SqLiteHelper {

	private static String TEMP_DIR = System.getProperty("user.home") + File.separator + ".stepicJavaWebServices1Temp" + File.separator + "sqlite" + File.separator;

	/**
	 * Создание БД
	 *
	 * @param name имя создаваемой БД
	 * @return url созданной БД
	 * @throws SQLException исключения при создании
	 */
	public static String createDB(String name) throws SQLException {
		File destFolder = new File(TEMP_DIR);
		if (!destFolder.exists()) {
			destFolder.mkdirs();
		}
		String url = getUrlFromName(name);

		if (new File(TEMP_DIR + name).exists()) {
			return url;
		}

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn == null) {
				throw new SQLException("Can't create database!");
			}
		}

		return url;
	}

	/**
	 * Удаление БД
	 *
	 * @param name имя удаляемой БД
	 */
	public static void deleteDB(String name) {
		File dbFile = new File(TEMP_DIR + name);
		if (dbFile.exists()) {
			dbFile.delete();
		}
	}

	/**
	 * Получить файл БД по имени
	 *
	 * @param dbName имя БД
	 * @return файл БД
	 */
	public static File getDbFileFromName(String dbName) {
		return new File(TEMP_DIR + dbName);
	}

	/**
	 * Получить url БД по имени
	 *
	 * @param dbName имя БД
	 * @return url БД
	 */
	public static String getUrlFromName(String dbName) {
		return "jdbc:sqlite:" + TEMP_DIR + dbName;
	}
}
