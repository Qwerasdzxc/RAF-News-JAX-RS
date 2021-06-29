package services;

import java.util.List;

import javax.inject.Inject;

import models.News;
import models.Tag;
import repositories.tag.TagRepository;

public class TagService {

	@Inject
	private TagRepository repository;
	

	public List<Tag> getTagsByNews(int newsId) {
		return repository.getTagsByNews(newsId);
	}
	

	public List<News> getNewsByTag(int tagId) {
		return repository.getNewsByTag(tagId);
	}
	

	public Tag createTag(int newsId, String keyword) {
		return repository.createTag(newsId, keyword);
	}
	

	public Tag findById(int tagId) {
		return repository.findById(tagId);
	}
	

	public Tag findByKeyword(String keyword) {
		return repository.findByKeyword(keyword);
	}
}
