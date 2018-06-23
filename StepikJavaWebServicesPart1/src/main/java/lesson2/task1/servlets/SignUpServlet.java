package lesson2.task1.servlets;

import lesson2.task1.accounts.AccountService;
import lesson2.task1.accounts.UserProfile;
import utils.BaseHttpServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет signup
 * При получении POST запроса на signup сервлет SignUpServlet должн запомнить логин и пароль в AccountService. После этого польователь с таким логином считается зарегистрированным.
 * Сервлет должен слушать POST запросы с параметрами login password
 *
 * @author Starovoytov
 * @since 11.05.2018
 */
public class SignUpServlet extends BaseHttpServlet {
	private final AccountService accountService;

	public SignUpServlet(AccountService accountService) {
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

		response.setContentType(HTTP_CONTENT_TYPE);
		response.getWriter().println("Ok!");
		response.setStatus(HttpServletResponse.SC_OK);

		accountService.addNewUser(new UserProfile(login, pass, null));
	}
}
