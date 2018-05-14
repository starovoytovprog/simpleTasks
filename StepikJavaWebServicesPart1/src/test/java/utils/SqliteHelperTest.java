package utils;

import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование utils.SqLiteHelper {@link SqLiteHelper}
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class SqliteHelperTest {
	/**
	 * Проверка результата выполнения метода
	 *
	 * @throws Exception исключения сервера
	 */
	@Test
	public void testRun() throws Exception {
		String testDbName = "db_fot_unit_test.sqlite";
		File dbFile = new File(System.getProperty("user.home") + File.separator + ".stepicJavaWebServices1Temp" + File.separator + "sqlite" + File.separator + testDbName);
		assertFalse(dbFile.exists());
		String dbUrl = SqLiteHelper.createDB(testDbName);
		Connection conn = DriverManager.getConnection(dbUrl);
		assertFalse(conn.isClosed());
		assertTrue(dbFile.exists());
		conn.close();
		SqLiteHelper.deleteDB(testDbName);
		assertFalse(dbFile.exists());
	}
}
