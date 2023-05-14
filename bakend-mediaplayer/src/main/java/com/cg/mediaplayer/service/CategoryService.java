package com.cg.mediaplayer.service;

import java.util.List;

import com.cg.mediaplayer.entity.Category;
import com.cg.mediaplayer.exception.RecordNotFoundException;




public interface CategoryService {
	public Category createCategory( Category category)throws RecordNotFoundException;
	public Category deleteCategory(int id) throws RecordNotFoundException;
	public Category updateCategory(Category category) throws RecordNotFoundException;
	public List<Category> viewAllCategories() throws RecordNotFoundException;
	


}

