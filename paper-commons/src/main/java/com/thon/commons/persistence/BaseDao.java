package com.thon.commons.persistence;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;

import java.util.List;

/**
 * @author 57
 */
public interface BaseDao<E extends IdEntity> {

	public E get(int id);

	public void save(E entity);

	public void delete(E entity);
	
	public void flush();

	public void clear();
	
	public Page<E> find(Page<E> page, DetachedCriteria detachedCriteria);

	public Page<E> find(Page<E> page, DetachedCriteria detachedCriteria, ResultTransformer resultTransformer);
	
	public List<E> find(DetachedCriteria detachedCriteria);
	
	public List<E> find(DetachedCriteria detachedCriteria, ResultTransformer resultTransformer);
	
	public DetachedCriteria createDetachedCriteria(Criterion... criterions);
	
	public List<E> findAll();

}
