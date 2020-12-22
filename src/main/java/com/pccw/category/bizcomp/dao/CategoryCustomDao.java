package com.pccw.category.bizcomp.dao;

import java.util.List;

import javax.persistence.Tuple;

public interface CategoryCustomDao {

	 List<Tuple> listCategory(List<String> fieldList, Integer offset, Integer limit);

}
