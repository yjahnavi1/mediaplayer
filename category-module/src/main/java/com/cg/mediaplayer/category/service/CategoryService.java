package com.cg.mediaplayer.category.service;

import java.util.List;

import com.cg.mediaplayer.category.entity.Category;
import com.cg.mediaplayer.category.exception.RecordNotFoundException;



public interface CategoryService {
	public List<Category> createCategory( Category category)throws RecordNotFoundException;
	public List<Category> deleteCategory(int id) throws RecordNotFoundException;
	public List<Category> updateCategory(Category category) throws RecordNotFoundException;
	public List<Category> viewAllCategories() throws RecordNotFoundException;
	
	


}

