package resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import models.News;
import services.NewsService;

@Path("/news")
public class NewsResource {
	
	@Inject
	private NewsService service;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public News findById(@PathParam("id") int newsId) {
		return service.findById(newsId);
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> searchNews(@QueryParam("page") int page, @QueryParam("query") String query) {
		return service.searchNews(page, query);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> getAllNews(@QueryParam("page") int page) {
		return service.getAllNews(page);
	}
	
	@GET
	@Path("/news-for-category")
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> getNewsForCategory(@QueryParam("page") int page, @QueryParam("categoryId") int categoryId) {
		return service.getNewsForCategory(page, categoryId);
	}
	
	@GET
	@Path("/recent-news")
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> getRecentNews() {
		return service.getRecentNews();
	}
	
	@GET
	@Path("/popular-news")
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> getPopularNews() {
		return service.getPopularNews();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public News createNews(@Valid News news) {
		return service.createNews(news.getCategoryId(), news.getAuthorId(), news.getTitle(), news.getContent());
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public News updateNews(@PathParam("id") int newsId, @Valid News news) {
		return service.updateNews(newsId, news.getCategoryId(), news.getTitle(), news.getContent());
	}
	
	@GET
	@Path("/news-for-category-count")
	@Produces(MediaType.TEXT_PLAIN)
	public Integer getNewsForCategoryCount(@QueryParam("categoryId") int categoryId) {
		return service.getNewsForCategoryCount(categoryId);
	}
	
	@DELETE
	public void deleteNews(@QueryParam("newsId") int newsId) {
		service.deleteNews(newsId);
	}
}
