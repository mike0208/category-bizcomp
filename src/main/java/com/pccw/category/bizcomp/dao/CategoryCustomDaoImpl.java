package com.pccw.category.bizcomp.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pccw.category.bizcomp.models.Category;

@Repository
@Transactional(readOnly = true)
public class CategoryCustomDaoImpl<T, ID extends Serializable> implements CategoryCustomDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Method to list the list of product Category
	 */
	@Override
	public List<Tuple> listCategory(List<String> fieldList, Integer offset, Integer limit) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = builder.createTupleQuery();
		Root<Category> root = cq.from(Category.class);
		Selection<?>[] fieldObjs = new Selection<?>[fieldList.size()];
		for (int i = 0; i < fieldList.size(); i++) {
			fieldObjs[i] = root.get(fieldList.get(i));
		}
		cq.multiselect(fieldObjs); /** Query returns list of Tuple objects **/
		TypedQuery<Tuple> query = entityManager.createQuery(cq);
		if (null != limit && null != offset) {
			query.setMaxResults(limit);
			query.setFirstResult(offset);
		}
		return query.getResultList();
	}
}

