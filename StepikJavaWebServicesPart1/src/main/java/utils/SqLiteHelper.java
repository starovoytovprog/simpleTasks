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

	public static String createDB(String name) throws SQLException {
		File destFolder = new File(TEMP_DIR);
		if (!destFolder.exists()) {
			destFolder.mkdirs();
		}
		String url = "jdbc:sqlite:" + TEMP_DIR + name;

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

	public static void deleteDB(String name) {
		File dbFile = new File(TEMP_DIR + name);
		if (dbFile.exists()) {
			dbFile.delete();
		}
	}
}
