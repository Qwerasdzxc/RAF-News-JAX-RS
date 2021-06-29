package repositories.tag;

import java.util.List;

import models.News;
import models.Tag;

public interface TagRepository {
	
	List<Tag> getTagsByNews(int newsId);
	
	List<News> getNewsByTag(int tagId);
	
	Tag createTag(int newsId, String keyword);
	
	Tag findById(int tagId);
	
	Tag findByKeyword(String keyword);
}
