package com.pccw.category.bizcomp.commons;


import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.Tuple;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class JsonConverter {

	/**
	 * Converts a list of tuple to a stringified JSON array.
	 *
	 * @param fields
	 * @param tuples
	 * @return
	 */
	public static String convert(List<String> fields, List<Tuple> tuples) {
		JsonArray jsonArr = new JsonArray();
		for (Tuple tuple : tuples) {
			JsonObject jsonObj = new JsonObject();
			for (int i = 0; i < fields.size(); i++) {
				jsonObj.addProperty(fields.get(i), (String) tuple.get(i));
			}
			jsonArr.add(jsonObj);
		}
		return jsonArr.toString();
	}

	/**
	 * Converts a list of entities to a stringified JSON array.
	 *
	 * @param entityList
	 * @param <T>
	 * @return
	 */
	public static <T> String convert(List<T> entityList) {
		Type listType = new TypeToken<List<T>>() {
		}.getType();
		Gson gson = new Gson();
		String json = gson.toJson(entityList, listType);
		return json;
	}

}
