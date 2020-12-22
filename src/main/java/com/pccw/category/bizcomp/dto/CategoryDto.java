package com.pccw.category.bizcomp.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor

public class CategoryDto {

	private String id = null;

	private String href = null;

	private String description = null;

	private Boolean isRoot = null;

	private LocalDateTime lastUpdate = null;

	private String lifecycleStatus = null;

	private String name = null;

	private String parentId = null;

	private String version = null;

	private List<ProductOfferingRef> productOffering = null;

	private List<CategoryRef> subCategory = null;

	private TimePeriod validFor = null;

	private String baseType = null;

	private String schemaLocation = null;

	private String type = null;
}
