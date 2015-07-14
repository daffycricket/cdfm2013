package com.gtanla.backend.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gtanla.backend.bo.Account;
import com.gtanla.backend.exception.BackendException;
import com.gtanla.backend.service.AccountService;

/**
 * Servlet used to see DB data add create data.
 * 
 * @author gtalbot
 * 
 */
public class CdfmTeam10 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2875603350042365832L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// Set response content
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello world. I'm the CDFM backend for TEAM-10");

		// Insert some data in DB if none.
		Collection<Account> accounts = new ArrayList<>();
		try {
			accounts.add(new Account("Nicolas"));
			accounts.add(new Account("Guillaume"));
			accounts.add(new Account("Arthur"));
			accounts.add(new Account("Marie"));
			accounts.add(new Account("Alexandre"));
			accounts.add(new Account("David"));
			accounts.add(new Account("Arnaud"));
			AccountService.create(accounts);
			accounts = AccountService.listAccounts();
			resp.getWriter().println(accounts.size() + " entity in DB");
		} catch (BackendException e) {
			resp.getWriter().println("Can not access to DB : " + e.getMessage());
		}

	}

}
