package lesson2.task1.servlets;

import com.google.gson.Gson;
import lesson2.task1.accounts.AccountService;
import lesson2.task1.accounts.UserProfile;
import utils.BaseHttpServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет обработки сессий. Источник - программа курса.
 *
 * @author Starovoytov
 * @since 08.05.2018
 */
public class SessionsServlet extends BaseHttpServlet {
	private final AccountService accountService;

	public SessionsServlet(AccountService accountService) {
		this.accountService = accountService;
	}

	//get logged user profile
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionId = request.getSession().getId();
		UserProfile profile = accountService.getUserBySessionId(sessionId);
		if (profile == null) {
			response.setContentType(JSON_CONTENT_TYPE);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		else {
			Gson gson = new Gson();
			String json = gson.toJson(profile);
			response.setContentType(JSON_CONTENT_TYPE);
			response.getWriter().println(json);
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}

	//sign in
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");

		if (login == null || pass == null) {
			response.setContentType(HTTP_CONTENT_TYPE);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		UserProfile profile = accountService.getUserByLogin(login);
		if (profile == null || !profile.getPass().equals(pass)) {
			response.setContentType(HTTP_CONTENT_TYPE);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		accountService.addSession(request.getSession().getId(), profile);
		Gson gson = new Gson();
		String json = gson.toJson(profile);
		response.setContentType(JSON_CONTENT_TYPE);
		response.getWriter().println(json);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	//sign out
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionId = request.getSession().getId();
		UserProfile profile = accountService.getUserBySessionId(sessionId);
		if (profile == null) {
			response.setContentType(HTTP_CONTENT_TYPE);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		else {
			accountService.deleteSession(sessionId);
			response.setContentType(HTTP_CONTENT_TYPE);
			response.getWriter().println("Goodbye!");
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}
}