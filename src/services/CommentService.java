package services;

import java.util.List;

import javax.inject.Inject;

import models.Comment;
import repositories.comment.CommentRepository;

public class CommentService {

	@Inject
	private CommentRepository repository;
	
	public List<Comment> getNewsComments(int newsId) {
		return repository.getNewsComments(newsId);
	}
	
	public Comment createComment(int newsId, String authorName, String content) {
		return repository.createComment(newsId, authorName, content);
	}
}
