package com.pccw.category.bizcomp.service;

import java.util.Map;

import com.pccw.category.bizcomp.models.Category;

public interface CategoryService {

	/**
	 * Creates a Category.
	 * 
	 * @param CategoryDto
	 * @return Category
	 */
	public Category createCategory(Category category);
	/**
	 * Retrieves a Category by ID.
	 * 
	 * @param id
	 * @param fields
	 * @return Category
	 */
	public Category retrieveCategoryById(String id, String fields);

	/**
	 * Updates partially a Category.
	 * 
	 * @param id
	 * @param CategoryDto
	 * @return Category
	 */
	
	public Category patchCategory(String id, Category category);

	/**
	 * Deletes a Category.
	 * 
	 * @param id
	 */
	public void deleteCategory(String id);
	
	/**
	 * List or find Category objects.
	 * 
	 * @return List<Category>
	 */
	public String listCategory(Map<String, String> reqParams) ;

	


}
