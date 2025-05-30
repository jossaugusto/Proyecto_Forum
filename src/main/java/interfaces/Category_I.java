package interfaces;

import java.util.List;

import entitys.Category_E;

public interface Category_I {
	// CRUD operations
	
	// List all categories
	List<Category_E> getAllCategories(String keyword, String order);
	
	List<Category_E> getAllDeletedCategories(String keyword, String order);
	
	// Get category by ID
	Category_E getCategoryById(int id);
	
	// Create a new category
	boolean createCategory(Category_E category);
	
	// Update category information
	boolean updateCategory(Category_E category);
	
	// Delete category by ID
	boolean deleteCategory(int id);

	boolean restoreCategory(int id);
	
	int countCategories();
}
