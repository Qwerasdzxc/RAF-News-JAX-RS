package services;

import java.util.List;

import javax.inject.Inject;

import models.Category;
import repositories.category.CategoryRepository;

public class CategoryService {

	@Inject
	private CategoryRepository repository;
	
	public List<Category> getCategories(int page) {
		return repository.getCategories(page);
	}
	
	public Category createCategory(String name, String description) {
		return repository.createCategory(name, description);
	}
	
	public Category updateCategory(int categoryId, String name, String description) {
		return repository.updateCategory(categoryId, name, description);
	}
	
	public Integer getCategoryCount() {
		return repository.getCategoryCount();
	}
	
	public void deleteCategory(int categoryId) {
		repository.deleteCategory(categoryId);
	}
}
