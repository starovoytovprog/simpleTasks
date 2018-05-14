package lesson2.task1.servlets;

import lesson2.task1.accounts.AccountService;
import lesson2.task1.accounts.UserProfile;
import utils.BaseHttpServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет signip
 * При получении POST запроса на signin, после регистрации, SignInServlet проверяет, логин/пароль пользователя.
 * Сервлет должен слушать POST запросы с параметрами login password
 *
 * @author Starovoytov
 * @since 11.05.2018
 */
public class SignInServlet extends BaseHttpServlet {
	private final AccountService accountService;

	public SignInServlet(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("password");

		if (login == null || pass == null) {
			response.setContentType(HTTP_CONTENT_TYPE);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		UserProfile profile = accountService.getUserByLogin(login);

		if (profile != null && profile.getPass().equals(pass)) {
			response.setContentType(HTTP_CONTENT_TYPE);
			response.getWriter().println("Authorized: " + login);
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			response.setContentType(HTTP_CONTENT_TYPE);
			response.getWriter().println("Unauthorized");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
}
