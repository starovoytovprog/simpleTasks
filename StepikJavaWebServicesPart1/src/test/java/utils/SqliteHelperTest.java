package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Тестирование utils.SqLiteHelper {@link SqLiteHelper}
 *
 * @author Starovoytov
 * @since 25.04.2018
 */
public class SqliteHelperTest {
	private static final String TEST_DB_NAME = "db_fot_unit_test.sqlite";
	private File dbFile;
	private String dbUrl;

	/**
	 * Создание БД для теста
	 *
	 * @throws SQLException исключения SQL
	 */
	@Before
	public void createDataBase() throws SQLException {
		dbFile = new File(System.getProperty("user.home") + File.separator + ".stepicJavaWebServices1Temp" + File.separator + "sqlite" + File.separator + TEST_DB_NAME);
		assertFalse(dbFile.exists());
		dbUrl = SqLiteHelper.createDB(TEST_DB_NAME);
	}

	/**
	 * Удалить БД после теста
	 */
	@After
	public void deleteDataBase() {
		SqLiteHelper.deleteDB(TEST_DB_NAME);
		assertFalse(dbFile.exists());
	}

	/**
	 * Проверка подключения к SQLite
	 *
	 * @throws Exception исключения сервера
	 */
	@Test
	public void testRun() throws Exception {
		Connection conn = DriverManager.getConnection(dbUrl);
		assertFalse(conn.isClosed());
		assertTrue(dbFile.exists());
		conn.close();
	}

	/**
	 * Проверка с SQLite с помощью hibernate
	 *
	 * @throws Exception исключения сервера
	 */
	@Test
	public void testRunHibernate() throws Exception {
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
		configuration.setProperty("hibernate.connection.url", dbUrl);
		configuration.setProperty("hibernate.connection.username", "");
		configuration.setProperty("hibernate.connection.password", "");
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create");
		configuration.addAnnotatedClass(TestEntity.class);

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = builder.build();

		try (SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry)) {
			long id = -1;
			String testData = "test data";
			try (Session session = sessionFactory.openSession()) {
				TestEntity testEntity = new TestEntity();
				testEntity.setData(testData);

				session.save(testEntity);
				id = testEntity.getId();
				session.flush();
			}
			try (Session session = sessionFactory.openSession()) {
				TestEntity testEntity = session.get(TestEntity.class, id);
				assertEquals(testData, testEntity.getData());
			}
		}
	}
}
