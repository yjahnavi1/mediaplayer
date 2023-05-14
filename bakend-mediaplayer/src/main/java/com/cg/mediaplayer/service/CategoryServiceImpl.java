package com.cg.mediaplayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mediaplayer.dao.CategoryDao;
import com.cg.mediaplayer.entity.Category;
import com.cg.mediaplayer.exception.RecordNotFoundException;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public Category createCategory(Category category) throws RecordNotFoundException{
		Category existingCategory = categoryDao.findByCategoryName(category.getCategoryName());
	if(existingCategory != null) {
		throw new RecordNotFoundException("Category already present!!");
	}
	return categoryDao.save(category);
	}

	@Override
	public  Category updateCategory(Category category) throws RecordNotFoundException {
		return  categoryDao.save( category);
	}

	@Override
	public  Category deleteCategory(int id) throws  RecordNotFoundException {
		 Category category = categoryDao.findById(id).get();
	    if (category.getId() != 0) {
	      categoryDao.deleteById( category.getId());
	    } else {
	      throw new RecordNotFoundException("Category not found!!");
	    }
	    return category;
	  }
	@Override
	public List<Category> viewAllCategories() throws RecordNotFoundException {
		List<Category> categories = categoryDao.findAll();
		if (categories.isEmpty()) {
			throw new RecordNotFoundException("No categories found!!");
		}
		return  categories;
	  }
}
	
