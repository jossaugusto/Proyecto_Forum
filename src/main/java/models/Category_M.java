package models;

import java.util.List;

import entitys.Category_E;
import interfaces.Category_I;

public class Category_M implements Category_I{

	@Override
	public List<Category_E> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category_E getCategoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createCategory(Category_E category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCategory(Category_E category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCategory(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Category_E> getCategoriesByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
