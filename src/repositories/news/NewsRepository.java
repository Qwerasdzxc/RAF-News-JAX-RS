package repositories.news;

import java.util.List;

import models.News;

public interface NewsRepository {
	
	News findById(int newsId);
	
	List<News> searchNews(int page, String query);
	
	List<News> getNewsForCategory(int page, int categoryId);
	
	List<News> getRecentNews();
	
	List<News> getPopularNews();
	
	News createNews(int categoryId, int authorId, String title, String content);
	
	News updateNews(int newsId, int categoryId, String title, String content);
	
	Integer getNewsForCategoryCount(int categoryId);
	
	void deleteNews(int newsId);

}
