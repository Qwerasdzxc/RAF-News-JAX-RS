package resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import models.News;
import models.Tag;
import services.TagService;

@Path("/tags")
public class TagResource {

	@Inject
	private TagService service;
	
	@GET
	@Path("/tags-by-news")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tag> getTagsByNews(@QueryParam("newsId") int newsId) {
		return service.getTagsByNews(newsId);
	}
	
	@GET
	@Path("/news-by-tag")
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> getNewsByTag(@QueryParam("tagId") int tagId) {
		return service.getNewsByTag(tagId);
	}
	
	@POST
	@Path("/{newsId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Tag createTag(@PathParam("newsId") int newsId, @Valid Tag tag) {
		return service.createTag(newsId, tag.getKeyword());
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Tag findById(@QueryParam("tagId") int tagId) {
		return service.findById(tagId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Tag findByKeyword(@QueryParam("keyword") String keyword) {
		return service.findByKeyword(keyword);
	}
}
