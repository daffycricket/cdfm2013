package com.gtanla.backend.resources;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;

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
import com.gtanla.backend.bo.Article;
import com.gtanla.backend.exception.BackendException;
import com.gtanla.backend.service.ArticleService;

/**
 * Entity resource mapped to path /articles
 */
@Path("/articles")
public class ArticleResource {

	/** Parameter IS is needed. */
	private static final String ERROR_MESSAGE_NO_PARAMETER = "Parameter ID is needed.";

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
	 * Creates a new article.
	 * 
	 * @param request
	 *            The JSON request
	 * @return A Response containing the newly created Article as a Json object.
	 * @throws IOException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createArticle(String request) throws IOException {

		try {
			// Retrieve entity from request
			Article article = (new Gson()).fromJson(request, Article.class);

			// Create the article
			Article res = ArticleService.create(article);

			// Return the identifier
			String jsonResult = (new Gson()).toJson(res);
			return Response.ok(jsonResult).build();

		} catch (BackendException e) {
			ArticleService.LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(AccountResource.STATUS_CODE_BUSINESS_ERROR).entity(e.toString()).build();
		} catch (Exception e) {
			ArticleService.LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(AccountResource.STATUS_CODE_SERVER_ERROR).entity(e.toString()).build();
		}
	}

	/**
	 * List all articles
	 * 
	 * @param id
	 *            Article identifier.
	 * @return All articles as a Json object.
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByid(@PathParam("id") String id) {

		// Prepare response
		Response response = null;

		try {

			// Check parameter
			response = Response.status(AccountResource.STATUS_CODE_BUSINESS_ERROR).entity(ERROR_MESSAGE_NO_PARAMETER)
					.build();

			// list articles
			Article article = ArticleService.get(Long.decode(id));

			// Serialize
			response = Response.ok((new Gson()).toJson(article)).build();

		} catch (Exception e) {
			ArticleService.LOG.log(Level.SEVERE, e.getMessage(), e);
			response = Response.status(AccountResource.STATUS_CODE_SERVER_ERROR).entity(e.toString()).build();
		}

		return response;
	}

	/**
	 * List all articles
	 * 
	 * @return All article as a Json object.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {

		// Prepare response
		Response response = null;

		try {
			// list articles
			Collection<Article> articles = ArticleService.listArticles();

			// Serialize
			response = Response.ok((new Gson()).toJson(articles)).build();

		} catch (Exception e) {
			ArticleService.LOG.log(Level.SEVERE, e.getMessage(), e);
			response = Response.status(AccountResource.STATUS_CODE_SERVER_ERROR).entity(e.toString()).build();
		}

		return response;
	}

	/**
	 * Update an article. If the article do not exist, return an error. Email, id, creationDate can not be modified.
	 * 
	 * @param request
	 *            The JSON request
	 * @return A Response containing the newly created Article as a Json object.
	 * @throws IOException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateArticle(String request) throws IOException {

		try {
			// Retrieve entity from request
			Article article = (new Gson()).fromJson(request, Article.class);

			// Update the article
			Article res = ArticleService.update(article);

			// Return the identifier
			String jsonResult = (new Gson()).toJson(res);
			return Response.ok(jsonResult).build();

		} catch (BackendException e) {
			ArticleService.LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(AccountResource.STATUS_CODE_BUSINESS_ERROR).entity(e.toString()).build();
		} catch (Exception e) {
			ArticleService.LOG.log(Level.SEVERE, e.getMessage(), e);
			return Response.status(AccountResource.STATUS_CODE_SERVER_ERROR).entity(e.toString()).build();
		}
	}
}