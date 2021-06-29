package resources;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Comment;
import services.CommentService;

@Path("/comments")
public class CommentResource {

	@Inject
	private CommentService service;
	
	@GET
	@Path("/{newsId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getNewsComments(@PathParam("newsId") int newsId) {
		return service.getNewsComments(newsId);
	}
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public Comment createComment(@Valid Comment comment) {
		return service.createComment(comment.getNewsId(), comment.getAuthorName(), comment.getContent());
	}
}
