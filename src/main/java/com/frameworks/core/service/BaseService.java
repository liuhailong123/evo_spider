package com.frameworks.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.frameworks.core.web.page.Pager;

public interface BaseService<T,PK extends Serializable> {
	
	T findById(PK id);
	
	void save(T entity);
	
	void update(T entity);
	
	void saveOrUpdate(T entity);
	
	void deleteById(PK id);
	
	void deleteByIds(PK[] ids);
	
	void deleteByEntity(T entity);
	
	void deleteByEntities(Iterable<T> entities);
	
	List<T> findAll();
	
	List<T> findAll(Pager webPage);
	
	List<T> findByCondition(Specification<T> specification, Pager webPage);//需要这个,但是需要穿一个Specification对象

	T findByCondition(Specification<T> spec);
	
	long count(Specification<T> spec);
}
