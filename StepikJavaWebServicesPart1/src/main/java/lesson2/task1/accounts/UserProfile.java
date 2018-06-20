package lesson2.task1.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Профиль пользователя. Источник - программа курса.
 *
 * @author Starovoytov
 * @since 08.05.2018
 */
@Entity
@Table(name = "user_profile")
public class UserProfile {
	@Id
	@Column(name = "login")
	private String login;
	@Column(name = "pass")
	private String pass;
	@Column(name = "email")
	private String email;

	public UserProfile() {}

	public UserProfile(String login, String pass, String email) {
		this.login = login;
		this.pass = pass;
		this.email = email;
	}

	public UserProfile(String login) {
		this.login = login;
		this.pass = login;
		this.email = login;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}