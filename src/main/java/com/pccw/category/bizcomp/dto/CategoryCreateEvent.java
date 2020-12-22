package com.pccw.category.bizcomp.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
public class CategoryCreateEvent {

	@JsonProperty("id")
	private String id = null;

	@JsonProperty("href")
	private String href = null;

	@JsonProperty("eventId")
	private String eventId = null;

	@JsonProperty("eventTime")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime eventTime = null;

	@JsonProperty("eventType")
	private String eventType = null;

	@JsonProperty("correlationId")
	private String correlationId = null;

	@JsonProperty("domain")
	private String domain = null;

	@JsonProperty("title")
	private String title = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("priority")
	private String priority = null;

	@JsonProperty("timeOcurred")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime timeOcurred = null;

	@JsonProperty("event")
	private CategoryCreateEventPayload event = null;

}
