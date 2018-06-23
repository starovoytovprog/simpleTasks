package lesson3.task1.accounts;

import lesson2.task1.accounts.AccountService;
import lesson2.task1.accounts.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import utils.SqLiteHelper;

import java.io.File;
import java.sql.SQLException;

/**
 * Сервис работы с аккаунтами в БД.
 *
 * @author Starovoytov
 * @since 08.05.2018
 */
public class DbAccountService extends AccountService {

	private static final String LESSON3_DB_NAME = "db_for_lesson3.sqlite";
	private ServiceRegistry serviceRegistry;
	private Configuration configuration = new Configuration();

	/**
	 * Конструктор по умолчанию
	 *
	 * @throws SQLException исключение, если не удалось подключиться к БД
	 */
	public DbAccountService() throws SQLException {
		super();

		File dbFile = SqLiteHelper.getDbFileFromName(LESSON3_DB_NAME);
		String dbUrl;

		if (dbFile.exists()) {
			dbUrl = SqLiteHelper.getUrlFromName(LESSON3_DB_NAME);
		}
		else {
			dbUrl = SqLiteHelper.createDB(LESSON3_DB_NAME);
		}

		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
		configuration.setProperty("hibernate.connection.url", dbUrl);
		configuration.setProperty("hibernate.connection.username", "");
		configuration.setProperty("hibernate.connection.password", "");
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
		configuration.addAnnotatedClass(UserProfile.class);

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		serviceRegistry = builder.build();
	}

	/**
	 * Добавить профиль в хранилище
	 *
	 * @param userProfile новый профиль
	 */
	public void addNewUser(UserProfile userProfile) {
		try (SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry)) {
			try (Session session = sessionFactory.openSession()) {
				session.save(userProfile);
				session.flush();
			}
		}
	}

	/**
	 * Получить профиль из хранилища по логину
	 *
	 * @param login логин
	 * @return профиль
	 */
	public UserProfile getUserByLogin(String login) {
		try (SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry)) {
			try (Session session = sessionFactory.openSession()) {
				return session.get(UserProfile.class, login);
			}
		}
	}
}
