package com.pccw.category.bizcomp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pccw.category.bizcomp.models.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category,String>, CategoryCustomDao{


	/**
	 * Query to retrieve a Category based on the given id
	 * 
	 * @return
	 */
	@Query("FROM Category category where category.id=:id")
	Category retrieveCategoryById(@Param("id") String id);

	/**
	 * Query to list of all ProductOfferings
	 * 
	 * @return
	 */
	@Query("FROM Category")
	List<Category> listCategory();
}
