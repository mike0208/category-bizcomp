package com.pccw.category.bizcomp.serviceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Tuple;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.category.bizcomp.commons.JsonConverter;
import com.pccw.category.bizcomp.constants.Constants;
import com.pccw.category.bizcomp.constants.Priority;
import com.pccw.category.bizcomp.dao.CategoryDao;
import com.pccw.category.bizcomp.dto.CategoryCreateEvent;
import com.pccw.category.bizcomp.dto.CategoryCreateEventPayload;
import com.pccw.category.bizcomp.exception.PersistenceException;
import com.pccw.category.bizcomp.models.Category;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl  {
	
	@Autowired
	CategoryDao categoryDao;

	//@Autowired
	//protected MessageBrokerClient client;

	private String createTopic = Constants.CREATE_TOPIC;


	public Category createCategory(Category category) {
		Category categoryObject = null;
		log.debug("createProductOffering in biz -> serviceImpl");
		try {
			if (category.getId() == null) {
				log.debug("createProductOffering in biz" + "createProductOffering.getId() == null");
				categoryObject = categoryDao.save(category);
				publishCreate(categoryObject);
			} else {
				log.debug("createProductOffering in biz" + "createProductOffering.getId() != null");
				categoryObject = categoryDao.findById(category.getId()).get();
			}
		} catch (PersistenceException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return categoryObject;
		
	}
	
	
	private void publish(String topic, Object publishedObject) {
		try {
			//client.produce(topic, publishedObject);
			log.info("Event published successfully to the topic: " + topic);
		} catch (Exception e) {
			log.error("Exception " + e.getMessage());
			throw e;
		}
	}

	
	private void publishCreate(Category category) {
		CategoryCreateEvent categoryCreateEvent = new CategoryCreateEvent();
		categoryCreateEvent.setId(category.getId());
		categoryCreateEvent.setHref(category.getHref());
		categoryCreateEvent.setEventId(UUID.randomUUID().toString());
		categoryCreateEvent.setEventTime(LocalDateTime.now());
		categoryCreateEvent.setEventType("Create Event");
		categoryCreateEvent.setDomain("Product Catalog");
		categoryCreateEvent.setTitle("productOfferingCreateEvent");
		categoryCreateEvent.setDescription("Create new Product offering event generated");
		categoryCreateEvent.setPriority(Priority.MEDIUM.toString());
		categoryCreateEvent.setTimeOcurred(category.getLastUpdate());
		categoryCreateEvent.setEvent(new CategoryCreateEventPayload(category));

		publish(createTopic, categoryCreateEvent);
	}
		
	
	public Category retrieveCategoryById(String id, String param) {
		
			log.info("retrieve category in Biz");
			log.debug("retrievecategoryById in biz -> serviceImpl");
			Category category = null;
			try {
				log.info("patch category in Biz");
				if (categoryDao.existsById(id)) {
					category = categoryDao.findById(id).get();
				}
			} catch (PersistenceException e) {
				log.error(e.getMessage(), e);
				throw e;
			}
			return category;
		}


	public Category patchCategory(String id, @Valid Category category) {
		Category categoryObj =null;
		try {
			log.info("patch ProductOffering in Biz");
			if (categoryDao.existsById(id)) {
				category.setId(id);
				categoryObj = categoryDao.save(category);
			}
		} catch (PersistenceException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return categoryObj;
	}


	public String listCategory(Map<String, String> reqParams) {
		String categoryList = null;
		Integer offset = null;
		Integer limit = null;
		if (reqParams.containsKey("offset") && reqParams.containsKey("limit")) {
			offset = Integer.parseInt(reqParams.get("offset"));
			limit = Integer.parseInt(reqParams.get("limit"));
		}
		try {
			log.debug("listProductOffering in biz");
			String fields = reqParams.getOrDefault("fields", null);
			List<String> fieldList = null;
			if (null == fields) {
				List<Category> productOfferingEntityList = categoryDao.listCategory();
				if (log.isDebugEnabled()) {
					log.debug("Collected " + productOfferingEntityList.size() + " documents from ProductOffering");
				}
				categoryList = new ObjectMapper().writeValueAsString(productOfferingEntityList);
			} else {
				if (fields.equals("none"))
					fields = Constants.DEFAULT_FILEDS;
				else
					fields = Constants.DEFAULT_FILEDS.concat(",").concat(fields);
				fieldList = Arrays.asList(fields.split(","));
				List<Tuple> productOfferingTuple = categoryDao.listCategory(fieldList, offset, limit);
				log.debug("Collected " + productOfferingTuple.size() + " documents from ProductOffering");
				categoryList = JsonConverter.convert(fieldList, productOfferingTuple);
			}
		} catch (JsonProcessingException je) {
			log.error(je.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return categoryList;
	}
	

}
