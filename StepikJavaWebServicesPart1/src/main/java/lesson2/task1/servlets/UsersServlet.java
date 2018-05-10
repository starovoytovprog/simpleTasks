package lesson2.task1.servlets;

import lesson2.task1.accounts.AccountService;
import utils.BaseHttpServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет обработки пользователей
 *
 * @author Starovoytov
 * @since 08.05.2018
 */
public class UsersServlet extends BaseHttpServlet
{
	@SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"}) //todo: remove after module 2 home work
	private final AccountService accountService;

	public UsersServlet(AccountService accountService)
	{
		this.accountService = accountService;
	}

	//get public user profile
	public void doGet(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException
	{
		//todo: module 2 home work
	}

	//sign up
	public void doPost(HttpServletRequest request,
	                   HttpServletResponse response) throws ServletException, IOException
	{
		//todo: module 2 home work
	}

	//change profile
	public void doPut(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException
	{
		//todo: module 2 home work
	}

	//unregister
	public void doDelete(HttpServletRequest request,
	                     HttpServletResponse response) throws ServletException, IOException
	{
		//todo: module 2 home work
	}
}