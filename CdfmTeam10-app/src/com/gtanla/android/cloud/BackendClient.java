package com.gtanla.android.cloud;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gtanla.android.cloud.bo.Account;
import com.gtanla.android.cloud.bo.Article;

/**
 * Client for all backend methods
 * 
 * @author gtalbot
 * 
 */
public class BackendClient {

	/** Gson type for List<Account>. */
	private static final TypeToken<List<Account>> ACCOUNT_LIST_TYPE = new TypeToken<List<Account>>() {
	};

	/** Backend url. */
	private static String backendUrl;

	/** Error message. */
	private static final String ERROR_MESSAGE_UNABLE_TO_PARSE_RESPONSE = "Unable to parse backend response";

	/** Instance. */
	private static BackendClient instance;

	/** Encoding type UTF8. */
	private static final String UTF_8 = "UTF-8";

	/**
	 * Get instance.
	 * 
	 * @return the current instance
	 */
	public static BackendClient getInstance() {
		if (instance == null) {
			instance = new BackendClient();
		}
		return instance;
	}

	/**
	 * Set backend server name
	 * 
	 * @param value
	 *            backend server name
	 * 
	 */
	public static void setBackendUrl(String value) {
		backendUrl = value;
	}

	/** HTTP client. */
	private final DefaultHttpClient client;

	/**
	 * Constructor.
	 */
	private BackendClient() {

		// Set HTTP client parameters
		client = new DefaultHttpClient();
		HttpParams httpParams = new BasicHttpParams();
		HttpProtocolParams.setContentCharset(httpParams, UTF_8);
		client.setParams(httpParams);
	}

	/**
	 * Get account by its identifier.
	 * 
	 * @param email
	 *            Account email
	 * @param password
	 *            Account password
	 * @return true if account exists
	 * @throws BackendException
	 *             Backend exception
	 */
	public Account checkAccount(String email, String password) throws BackendException {
		try {
			// Prepare request
			HttpGet request = createNewHttpGet("/rest/accounts/check");
			request.addHeader("Cookie", MessageFormat.format("EMAIL={0}; PASSWORD={1}", email, password));

			// execute request
			HttpResponse response = client.execute(request);

			// Check http status code
			checkHttpResponseStatus(response);

			// Parse json results
			Gson gson = new Gson();
			Account account = gson.fromJson(new InputStreamReader(response.getEntity().getContent()), Account.class);

			// Return result
			return account;

		} catch (ClientProtocolException e) {
			throw new BackendException(e);
		} catch (IOException e) {
			throw new BackendException(e);
		}
	}

	/**
	 * Check http status code in response. If not 200, throw an exception.
	 * 
	 * @param response
	 *            HTTP response
	 * @throws BackendException
	 */
	private void checkHttpResponseStatus(HttpResponse response) throws BackendException {

		// If status is not OK
		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
				throw new BackendException("Invalid login / password");
			} else {
				throw new BackendException("Error during cloud call : " + extractErrorMessage(response));
			}
		}
	}

	/**
	 * @param account
	 *            An account to create
	 * @return The account identifier
	 * @throws BackendException
	 *             A backend exception
	 */
	public Account createAccount(Account account) throws BackendException {

		try {
			// Prepare data to send
			Gson gson = new Gson();
			String data = gson.toJson(account);

			// Prepare request
			HttpPost request = createNewHttpPost("/rest/accounts");
			request.setEntity(new StringEntity(data));

			// Execute request
			HttpResponse response = client.execute(request);

			// Check http status code
			checkHttpResponseStatus(response);

			// Parse json results
			Gson gsonResponse = new Gson();
			Account res = gsonResponse
					.fromJson(new InputStreamReader(response.getEntity().getContent()), Account.class);

			// Return result
			return res;

		} catch (UnsupportedEncodingException e) {
			throw new BackendException(e);
		} catch (ClientProtocolException e) {
			throw new BackendException(e);
		} catch (IOException e) {
			throw new BackendException(e);
		}
	}

	/**
	 * @param article
	 *            An article to create
	 * @return The article identifier
	 * @throws BackendException
	 *             A backend exception
	 */
	public Article createArticle(Article article) throws BackendException {

		try {
			// Prepare data to send
			Gson gson = new Gson();
			String data = gson.toJson(article);

			// Prepare request
			HttpPost request = createNewHttpPost("/rest/articles");
			request.setEntity(new StringEntity(data));

			// Execute request
			HttpResponse response = client.execute(request);

			// Check http status code
			checkHttpResponseStatus(response);

			// Parse json results
			Gson gsonResponse = new Gson();
			Article res = gsonResponse
					.fromJson(new InputStreamReader(response.getEntity().getContent()), Article.class);

			// Return result
			return res;

		} catch (UnsupportedEncodingException e) {
			throw new BackendException(e);
		} catch (ClientProtocolException e) {
			throw new BackendException(e);
		} catch (IOException e) {
			throw new BackendException(e);
		}
	}

	/**
	 * Create a new GET request
	 * 
	 * @param uri
	 *            URI
	 * @return a GET request
	 */
	private HttpGet createNewHttpGet(String uri) {
		HttpGet request = new HttpGet("http://" + backendUrl + uri);
		request.setHeader("Content-type", "application/json; charset=UTF-8");
		request.setHeader("Accept-Language", "fr-FR");
		return request;
	}

	/**
	 * Create a new GET request
	 * 
	 * @param uri
	 *            URI
	 * @return a GET request
	 * @throws BackendException
	 *             A backend exception
	 */
	private HttpPost createNewHttpPost(String uri) {
		HttpPost request = new HttpPost("http://" + backendUrl + uri);
		request.setHeader("Content-type", "application/json; charset=UTF-8");
		request.setHeader("Accept-Language", "fr-FR");
		return request;
	}

	/**
	 * Create a new GET request
	 * 
	 * @param uri
	 *            URI
	 * @return a GET request
	 * @throws BackendException
	 *             A backend exception
	 */
	private HttpPut createNewHttpPut(String uri) {
		HttpPut request = new HttpPut("http://" + backendUrl + uri);
		request.setHeader("Content-type", "application/json; charset=UTF-8");
		request.setHeader("Accept-Language", "fr-FR");
		return request;
	}

	/**
	 * Extract an error message for the HTTP response.
	 * 
	 * @param response
	 *            The HTTP response
	 * @return The error message.
	 */
	private String extractErrorMessage(HttpResponse response) {
		Scanner s = null;
		String message = null;
		try {
			s = new Scanner(new InputStreamReader(response.getEntity().getContent())).useDelimiter("\\A");
			if (s.hasNext()) {
				message = s.next();
			} else {
				message = ERROR_MESSAGE_UNABLE_TO_PARSE_RESPONSE;
			}
		} catch (IllegalStateException e) {
			message = ERROR_MESSAGE_UNABLE_TO_PARSE_RESPONSE;
		} catch (IOException e) {
			message = ERROR_MESSAGE_UNABLE_TO_PARSE_RESPONSE;
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return message;
	}

	/**
	 * Get account by its identifier.
	 * 
	 * @param id
	 *            Account identifier
	 * @return An account
	 * @throws BackendException
	 *             backend exception
	 */
	public Account getAccount(Long id) throws BackendException {
		try {
			// Prepare request
			HttpGet request = createNewHttpGet("/rest/accounts/" + id);

			// execute request
			HttpResponse response = client.execute(request);

			// Check http status code
			checkHttpResponseStatus(response);

			// Parse json results
			Gson gson = new Gson();
			Account account = gson.fromJson(new InputStreamReader(response.getEntity().getContent()), Account.class);

			// Return result
			return account;

		} catch (ClientProtocolException e) {
			throw new BackendException(e);
		} catch (IOException e) {
			throw new BackendException(e);
		}
	}

	/**
	 * Get article by its identifier.
	 * 
	 * @param id
	 *            Article identifier
	 * @return An article
	 * @throws BackendException
	 *             backend exception
	 */
	public Article getArticle(Long id) throws BackendException {
		try {
			// Prepare request
			HttpGet request = createNewHttpGet("/rest/articles/" + id);

			// execute request
			HttpResponse response = client.execute(request);

			// Check http status code
			checkHttpResponseStatus(response);

			// Parse json results
			Gson gson = new Gson();
			Article article = gson.fromJson(new InputStreamReader(response.getEntity().getContent()), Article.class);

			// Return result
			return article;

		} catch (ClientProtocolException e) {
			throw new BackendException(e);
		} catch (IOException e) {
			throw new BackendException(e);
		}
	}

	/**
	 * List all accounts
	 * 
	 * @return all database accounts
	 * @throws BackendException
	 *             backend exception
	 */
	public Collection<Account> listAccounts() throws BackendException {

		try {
			// Prepare request
			HttpGet request = createNewHttpGet("/rest/accounts");

			// execute request
			HttpResponse response = client.execute(request);

			// Check http status code
			checkHttpResponseStatus(response);

			// Parse json results
			Gson gson = new Gson();
			List<Account> accounts = gson.fromJson(new InputStreamReader(response.getEntity().getContent()),
					ACCOUNT_LIST_TYPE.getType());

			// Return result
			return accounts;

		} catch (ClientProtocolException e) {
			throw new BackendException(e);
		} catch (IOException e) {
			throw new BackendException(e);
		}
	}

	/**
	 * List all articles
	 * 
	 * @return all database articles
	 * @throws BackendException
	 *             backend exception
	 */
	public Collection<Article> listArticles() throws BackendException {

		try {
			// Prepare request
			HttpGet request = createNewHttpGet("/rest/articles");

			// execute request
			HttpResponse response = client.execute(request);

			// Check http status code
			checkHttpResponseStatus(response);

			// Parse json results
			Gson gson = new Gson();
			List<Article> articles = gson.fromJson(new InputStreamReader(response.getEntity().getContent()),
					ACCOUNT_LIST_TYPE.getType());

			// Return result
			return articles;

		} catch (ClientProtocolException e) {
			throw new BackendException(e);
		} catch (IOException e) {
			throw new BackendException(e);
		}
	}

	/**
	 * Call the renew service. 100% async, don't wait for the result.
	 * 
	 * @param email
	 *            An email whose password is to renew
	 * @throws BackendException
	 *             A backend exception
	 */
	public void renewPassword(String email) throws BackendException {

		try {
			// Prepare request
			HttpPut request = createNewHttpPut("/rest/accounts/email");
			request.setEntity(new StringEntity(email));

			// Execute request
			client.execute(request);

		} catch (UnsupportedEncodingException e) {
			throw new BackendException(e);
		} catch (ClientProtocolException e) {
			throw new BackendException(e);
		} catch (IOException e) {
			throw new BackendException(e);
		}
	}

	/**
	 * Call the update request
	 * 
	 * @param account
	 *            An account to create
	 * @return The account identifier
	 * @throws BackendException
	 *             A backend exception
	 */
	public Account updateAccount(Account account) throws BackendException {

		try {
			// Prepare data to send
			Gson gson = new Gson();
			String data = gson.toJson(account);

			// Prepare request
			HttpPut request = createNewHttpPut("/rest/accounts");
			request.setEntity(new StringEntity(data));

			// Execute request
			HttpResponse response = client.execute(request);

			// Check http status code
			checkHttpResponseStatus(response);

			// Parse json results
			Gson gsonResponse = new Gson();
			Account res = gsonResponse
					.fromJson(new InputStreamReader(response.getEntity().getContent()), Account.class);

			// Return result
			return res;

		} catch (UnsupportedEncodingException e) {
			throw new BackendException(e);
		} catch (ClientProtocolException e) {
			throw new BackendException(e);
		} catch (IOException e) {
			throw new BackendException(e);
		}
	}

	/**
	 * Call the update request
	 * 
	 * @param article
	 *            An article to create
	 * @return The article identifier
	 * @throws BackendException
	 *             A backend exception
	 */
	public Article updateArticle(Article article) throws BackendException {

		try {
			// Prepare data to send
			Gson gson = new Gson();
			String data = gson.toJson(article);

			// Prepare request
			HttpPut request = createNewHttpPut("/rest/articles");
			request.setEntity(new StringEntity(data));

			// Execute request
			HttpResponse response = client.execute(request);

			// Check http status code
			checkHttpResponseStatus(response);

			// Parse json results
			Gson gsonResponse = new Gson();
			Article res = gsonResponse
					.fromJson(new InputStreamReader(response.getEntity().getContent()), Article.class);

			// Return result
			return res;

		} catch (UnsupportedEncodingException e) {
			throw new BackendException(e);
		} catch (ClientProtocolException e) {
			throw new BackendException(e);
		} catch (IOException e) {
			throw new BackendException(e);
		}
	}
}
