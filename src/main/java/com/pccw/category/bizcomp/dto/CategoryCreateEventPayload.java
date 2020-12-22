package com.pccw.category.bizcomp.dto;


import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pccw.category.bizcomp.models.Category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Validated
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
public class CategoryCreateEventPayload implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("category")
	private Category category = null;
}


