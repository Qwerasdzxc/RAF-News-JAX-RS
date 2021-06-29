package repositories.category;

import java.util.List;

import models.Category;

public interface CategoryRepository {
	
	Category findById(int categoryId);

	List<Category> getCategories(int page);
	
	Category createCategory(String name, String description);
	
	Category updateCategory(int categoryId, String name, String description);
	
	Integer getCategoryCount();
	
	void deleteCategory(int categoryId);
}
