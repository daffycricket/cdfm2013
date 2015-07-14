package com.gtanla.backend.resources;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.gtanla.backend.bo.Account;
import com.gtanla.backend.exception.BackendException;
import com.gtanla.backend.service.AccountService;

/**
 * Entity resource mapped to path /accounts
 */
@Path("/accounts")
public class AccountResource {

	/** Equals for cookie. */
	private static final String COOKIE_EQUALS = "=";

	/** Cookie name for EMAIL. */
	private static final String COOKIE_NAME_EMAIL = "EMAIL";

	/** Cookie name for PASSWORD. */
	private static final String COOKIE_NAME_PASSWORD = "PASSWORD";

	/** Cookie separator. */
	private static final String COOKIE_SEPARATOR = ";";

	/** Parameter IS is needed. */
	private static final String ERROR_MESSAGE_NO_PARAMETER = "Parameter ID is needed.";

	/** log helper. */
	public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/** HTTP status code : 400. */
	public static final int STATUS_CODE_BUSINESS_ERROR = 400;

	/** HTTP status code : 401. */
	private static final int STATUS_CODE_FORBIDDEN = 401;

	/** HTTP status code : 500. */
	public static final int STATUS_CODE_SERVER_ERROR = 500;

	/**
	 * Allows to insert http headers into the class.
	 */
	@Context
	private HttpHeaders headers;

	/**
	 * Allows to insert UriInfo into the class.
	 */
	@Context
	private UriInfo uriInfo;

	/**
	 * Check the account exists. All parameters are in headers.
	 * 
	 * @return a JSON stream
	 */
	@GET
	@Path("check")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkAccountExists() {
		// Prepare response
		Response response = null;

		try {

			// get email/mdp from headers
			String email = null;
			String password = null;
			List<String> cookieHeaderss = headers.getRequestHeader(HttpHeaders.COOKIE);
			if (cookieHeaderss != null && cookieHeaderss.size() > 0 && cookieHeaderss.get(0) != null) {
				String[] cookieHeaders = cookieHeaderss.get(0).split(COOKIE_SEPARATOR);

				for (String cookieHeader : cookieHeaders) {
					if (!cookieHeader.contains(COOKIE_EQUALS)) {
						break;
					}

					String[] cookieHeaderParts = cookieHeader.split(COOKIE_EQUALS);
					if (cookieHeaderParts[0].trim().equalsIgnoreCase(COOKIE_NAME_EMAIL)) {
						email = cookieHeaderParts[1];
					} else if (cookieHeaderParts[0].trim().equalsIgnoreCase(COOKIE_NAME_PASSWORD)) {
						password = cookieHeaderParts[1];
					}
				}
			}

			// list accounts
			Account account = AccountService.checkFullAuthentication(email, password);
			if (account != null) {
				response = Response.ok((new Gson()).toJson(account)).build();
			} else {
				response = Response.status(STATUS_CODE_FORBIDDEN).build();
			}

			// Serialize

		} catch (Exception e) {
			AccountService.LOG.log(Level.SEVERE, e.getMessage(), e);
			response = Response.status(STATUS_CODE_SERVER_ERROR).entity(e.toString()).build();
		}

		return response;
	}

	/**
	 * List all accounts
	 * 
	 * @param id
	 *            Account identifier.
	 * @return All accounts as a Json object.
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByid(@PathParam("id") String id) {

		// Prepare response
		Response response = null;

		try {

			// Check parameter
			response = Response.status(STATUS_CODE_BUSINESS_ERROR).entity(ERROR_MESSAGE_NO_PARAMETER).build();

			// list accounts
			Account account = AccountService.get(Long.decode(id));

			// Serialize
			response = Response.ok((new Gson()).toJson(account)).build();

		} catch (Exception e) {
			AccountService.LOG.log(Level.SEVERE, e.getMessage(), e);
			response = Response.status(STATUS_CODE_SERVER_ERROR).entity(e.toString()).build();
		}

		return response;
	}

	/**
	 * List all accounts
	 * 
	 * @return All account as a Json object.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {

		// Prepare response
		Response response = null;

		try {
			// list accounts
			Collection<Account> accounts = AccountService.listAccounts();

			// Serialize
			response = Response.ok((new Gson()).toJson(accounts)).build();

		} catch (Exception e) {
			AccountService.LOG.log(Level.SEVERE, e.getMessage(), e);
			response = Response.status(STATUS_CODE_SERVER_ERROR).entity(e.toString()).build();
		}

		return response;
	}

	/**
	 * Creates a new account.
	 * 
	 * @param request
	 *            The JSON request
	 * @return A Response containing the newly created Account as a Json object.
	 * @throws IOException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerAccount(String request) throws IOException {

		try {
			// Retrieve entity from request
			Account account = (new Gson()).fromJson(request, Account.class);

			// Create the account
			Account res = AccountService.create(account);

			// Return the identifier
			String jsonResult = (new Gson()).toJson(res);
			return Response.ok(jsonResult).build();

		} catch (BackendException e) {
			AccountService.LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(STATUS_CODE_BUSINESS_ERROR).entity(e.toString()).build();
		} catch (Exception e) {
			AccountService.LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(STATUS_CODE_SERVER_ERROR).entity(e.toString()).build();
		}
	}

	/**
	 * Renews password for given account and send email. If the account do not exist, return an error.
	 * 
	 * @param request
	 *            The JSON request
	 * @return A Response containing the newly created Account as a Json object.
	 * @throws IOException
	 */
	@PUT
	@Path("email")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response renewPassword(String request) throws IOException {

		try {
			// Retrieve entity from request
			// Account account = (new Gson()).fromJson(request, Account.class);
			String email = request;

			// get the account
			Account account = AccountService.checkUserExistForEmail(email);

			// if exists, modify password then send email
			if (account != null) {

				// modify password
				String newPassword = "a" + account.getPassword();
				account.setPassword(newPassword);
				AccountService.update(account);

				// send email
				Properties props = new Properties();
				Session session = Session.getDefaultInstance(props, null);
				try {
					Message msg = new MimeMessage(session);
					msg.setFrom(new InternetAddress("laurent.nicolas@gmail.com", "Admin #MODDV"));
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email, "Admin #MODDV"));
					msg.setSubject("Modification du mot de passe");
					msg.setText("Vous pouvez vous connecter sur MODDV avec votre nouveau mot de passe : " + newPassword);
					Transport.send(msg);
					LOGGER.info("Sent From " + msg.getFrom() + " To " + email);
					LOGGER.info("Account=" + new Gson().toJson(account));
				} catch (AddressException e) {
					LOGGER.severe("Email - AddressException=" + e.getMessage());
				} catch (MessagingException e) {
					LOGGER.severe("Email - MessagingException=" + e.getMessage());
				} catch (Exception e) {
					LOGGER.severe("Email - Exception=" + e.getMessage());
				}
			}

			return Response.ok().build();
		} catch (BackendException e) {
			AccountService.LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(STATUS_CODE_BUSINESS_ERROR).entity(e.toString()).build();
		} catch (Exception e) {
			AccountService.LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(STATUS_CODE_SERVER_ERROR).entity(e.toString()).build();
		}
	}

	/**
	 * Update an account. If the account do not exist, return an error. Email, id, creationDate can not be modified.
	 * 
	 * @param request
	 *            The JSON request
	 * @return A Response containing the newly created Account as a Json object.
	 * @throws IOException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAccount(String request) throws IOException {

		try {
			// Retrieve entity from request
			Account account = (new Gson()).fromJson(request, Account.class);

			// Update the account
			Account res = AccountService.update(account);

			// Return the identifier
			String jsonResult = (new Gson()).toJson(res);
			return Response.ok(jsonResult).build();

		} catch (BackendException e) {
			AccountService.LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(STATUS_CODE_BUSINESS_ERROR).entity(e.toString()).build();
		} catch (Exception e) {
			AccountService.LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(STATUS_CODE_SERVER_ERROR).entity(e.toString()).build();
		}
	}
}