package services;

import java.util.List;

import javax.inject.Inject;

import models.News;
import repositories.news.NewsRepository;

public class NewsService {
	
	@Inject
	private NewsRepository repository;
	
	public News findById(int newsId) {
		return repository.findById(newsId);
	}
	
	public List<News> searchNews(int page, String query) {
		return repository.searchNews(page, query);
	}
	
	public List<News> getAllNews(int page) {
		return repository.getAllNews(page);
	}
	
	public List<News> getNewsForCategory(int page, int categoryId) {
		return repository.getNewsForCategory(page, categoryId);
	}
	
	public List<News> getRecentNews() {
		return repository.getRecentNews();
	}
	
	public List<News> getPopularNews() {
		return repository.getPopularNews();
	}
	
	public News createNews(int categoryId, int authorId, String title, String content) {
		return repository.createNews(categoryId, authorId, title, content);
	}
	
	public News updateNews(int newsId, int categoryId, String title, String content) {
		return repository.updateNews(newsId, categoryId, title, content);
	}
	
	public Integer getNewsForCategoryCount(int categoryId) {
		return repository.getNewsForCategoryCount(categoryId);
	}
	
	public void deleteNews(int newsId) {
		repository.deleteNews(newsId);
	}
}
