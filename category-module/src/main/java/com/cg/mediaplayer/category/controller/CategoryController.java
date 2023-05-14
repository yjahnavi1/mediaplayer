package com.cg.mediaplayer.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.cg.mediaplayer.category.entity.Category;
import com.cg.mediaplayer.category.exception.RecordNotFoundException;
import com.cg.mediaplayer.category.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins="http://localhost:4200")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/createCategory")
	public List<Category> createCategory(@RequestBody Category category) throws RecordNotFoundException{
		System.out.println(category);
		return this.categoryService.createCategory(category);
	}
	
	@PutMapping("/updateCategory")
	public List<Category> updateCategory(@RequestBody Category category) throws RecordNotFoundException {
		return this.categoryService.updateCategory(category);
		}
	  
	@DeleteMapping("/deleteCategoryById/{id}")
	public ResponseEntity<HttpStatus> deleteCategory(@PathVariable int id) throws RecordNotFoundException {
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<>(HttpStatus.OK);
	  }
	
	@GetMapping("/viewAllCategories")
	public List<Category> viewAllCategories() throws RecordNotFoundException {
		return this.categoryService.viewAllCategories();
	  }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(RecordNotFoundException.class) // local to the RestController
	public final ResponseEntity<String> handleRecordNotFoundException(Exception ex, WebRequest request) {
	 //logger.error(ex.getMessage());
	 return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
