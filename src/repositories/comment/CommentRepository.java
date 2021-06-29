package repositories.comment;

import java.util.List;

import models.Comment;

public interface CommentRepository {
	
	List<Comment> getNewsComments(int newsId);
	
	Comment createComment(int newsId, String authorName, String content);

}
