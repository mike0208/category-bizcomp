package com.pccw.category.bizcomp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pccw.category.bizcomp.exception.CustomException;
import com.pccw.category.bizcomp.exception.PersistenceException;
import com.pccw.category.bizcomp.models.Category;
import com.pccw.category.bizcomp.serviceImpl.CategoryServiceImpl;

import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
public class CategoryBizController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> createCategory(
			@ApiParam(value = "The category to be created", required = true) @Valid @RequestBody Category category) {
		log.info("categoryBizcomp controller createcategory");
		Category categoryObject = null;
		try {
			categoryObject = categoryServiceImpl.createCategory(category);
			return new ResponseEntity<>(categoryObject, HttpStatus.CREATED);
		} catch (PersistenceException e) {
			log.error(e.getMessage(), e);

		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> retrieveCategory(
			@ApiParam(value = "Identifier of the category", required = true) @PathVariable("id") String id,
			@ApiParam(value = "Comma-separated properties to provide in response") @Valid @RequestParam(value = "fields", required = false) String fields) {
		log.info("ProductcategoryBizcomp controller retrievecategory");
		try {
			Category category = categoryServiceImpl.retrieveCategoryById(id, "");
			return new ResponseEntity<>(category, HttpStatus.OK);
		} catch (PersistenceException e) {
			log.error(e.getMessage(), e);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PatchMapping(value = "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> patchProductOffering(
			@ApiParam(value = "Identifier of the category", required = true) @PathVariable("id") String id,
			@ApiParam(value = "The category to be updated", required = true) @Valid @RequestBody Category category) {
		try {
			Map<String, Object> pathParams = new HashMap<>();
			if (null != id) {
				log.info("ID is: " + id);
				pathParams.put("id", id);
			}
			log.info("Updating a product category object for the given ID");
			Category categoryObject = categoryServiceImpl.patchCategory(id, category);
			return new ResponseEntity<>(categoryObject, HttpStatus.OK);
		} catch (CustomException e) {
			log.error(e.getDetails());
			throw e;
		}
	}
	
	@GetMapping(value = "/category")
	public ResponseEntity<String> listProductOffering(@RequestParam(required = false) Map<String, String> reqParams) {
		log.info("ProductcategoryBizcomp controller listProductcategory");
		try {
			String result = categoryServiceImpl.listCategory(reqParams);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
	
