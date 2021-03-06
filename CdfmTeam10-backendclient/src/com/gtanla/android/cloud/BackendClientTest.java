package com.gtanla.android.cloud;

import java.util.Collection;
import java.util.UUID;

import com.gtanla.android.cloud.bo.Account;
import com.gtanla.android.cloud.bo.Article;

/**
 * Unit test for all CLOUD resources
 * 
 * @author gtalbot
 * 
 */
public class BackendClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Set backend url
		BackendClient.setBackendUrl("localhost:8888");

		// Run tests
		boolean result = testAccountResources();
		result &= testArticleResources();

		// Log end
		if (result) {
			System.out.println("SUCCESS");
		} else {
			System.err.println("ERROR DURING TEST");
		}
	}

	/**
	 * Test resources for ACCOUNT
	 */
	private static boolean testAccountResources() {
		boolean result = true;

		// List accounts
		try {
			Collection<Account> res = BackendClient.getInstance().listAccounts();
			System.out.println(res);
		} catch (BackendException e) {
			e.printStackTrace();
			result = false;
		}

		// Create account
		Long id = null;
		String email = "test_" + UUID.randomUUID() + "@gmail.com";
		String password = "password";
		String name = "TestClientRest";
		String name2 = "TestClientRest2";
		try {
			Account storedAccount = BackendClient.getInstance().createAccount(
					new Account("address", "city", "cp", email, name, null, "TestClientRest", password));
			System.out.println(storedAccount);
			id = storedAccount.getId();
		} catch (BackendException e) {
			e.printStackTrace();
			result = false;
		}

		// Get account
		try {
			Account account = BackendClient.getInstance().getAccount(id);
			System.out.println(account);
		} catch (BackendException e) {
			e.printStackTrace();
			result = false;
		}

		// Check account exists
		Account account = null;
		try {
			account = BackendClient.getInstance().checkAccount(email, password);
			System.out.println(account);
		} catch (BackendException e) {
			e.printStackTrace();
			result = false;
		}

		// Update account exists
		try {
			account.setName(name2);
			Account modifiedAccount = BackendClient.getInstance().updateAccount(account);
			if (name.equals(modifiedAccount.getName())) {
				System.err.println("Erreur : pas de modification sauvegardées");
				result = false;
			}
			modifiedAccount = BackendClient.getInstance().getAccount(account.getId());
			if (!name2.equals(modifiedAccount.getName())) {
				System.err.println("Erreur : pas de modification sauvegardées");
				result = false;
			}
			System.out.println(account);
		} catch (BackendException e) {
			e.printStackTrace();
			result = false;
		}

		// Update email
		try {
			BackendClient.getInstance().renewPassword("test_25e638b0-46f5-4cba-9c15-611083138836@gmail.com");
			System.out.println(account);
		} catch (BackendException e) {
			e.printStackTrace();
			result = false;
		}

		// return result flag
		return result;
	}

	/**
	 * Test resources for ARTICLE
	 */
	private static boolean testArticleResources() {
		boolean result = true;

		// List articles
		try {
			Collection<Article> res = BackendClient.getInstance().listArticles();
			System.out.println(res);
		} catch (BackendException e) {
			e.printStackTrace();
			result = false;
		}

		// Create article
		Long id = null;
		String type = "test_" + UUID.randomUUID();
		String content = "content";
		String content2 = "content2";
		try {
			Article storedArticle = BackendClient.getInstance().createArticle(new Article(type, content));
			System.out.println(storedArticle);
			id = storedArticle.getId();
		} catch (BackendException e) {
			e.printStackTrace();
			result = false;
		}

		// Get article
		Article article = null;
		try {
			article = BackendClient.getInstance().getArticle(id);
			System.out.println(article);
		} catch (BackendException e) {
			e.printStackTrace();
			result = false;
		}

		// Update article exists
		try {
			article.setContent(content2);
			Article modifiedArticle = BackendClient.getInstance().updateArticle(article);
			if (content.equals(modifiedArticle.getContent())) {
				System.err.println("Erreur : pas de modification sauvegardées");
				result = false;
			}
			modifiedArticle = BackendClient.getInstance().getArticle(article.getId());
			if (!content2.equals(modifiedArticle.getContent())) {
				System.err.println("Erreur : pas de modification sauvegardées");
				result = false;
			}
			System.out.println(article);
		} catch (BackendException e) {
			e.printStackTrace();
			result = false;
		}

		// return result flag
		return result;
	}
}
