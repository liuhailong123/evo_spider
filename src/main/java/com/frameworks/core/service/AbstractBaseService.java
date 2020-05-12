package com.frameworks.core.service;

import com.frameworks.core.repository.BaseRepository;
import com.frameworks.core.web.page.Pager;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractBaseService<T, PK extends Serializable> implements BaseService<T, PK> {

    protected abstract BaseRepository<T, PK> getBaseRepository();

    @Override
    public T findById(PK id) {
        return getBaseRepository().findOne(id);
    }

    public void save(Iterable<T> entities) {
        getBaseRepository().save(entities);
    }

    @Override
    public void save(T entity) {
        this.saveOrUpdate(entity);
    }

    @Override
    public void update(T entity) {
        this.saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        getBaseRepository().save(entity);
    }

    @Override
    public void deleteById(PK id) {
        getBaseRepository().delete(id);
    }

    @Override
    public void deleteByIds(PK[] ids) {
        for (PK id : ids) {
            getBaseRepository().delete(id);
        }
    }

    @Override
    public void deleteByEntity(T entity) {
        getBaseRepository().delete(entity);
    }

    @Override
    public void deleteByEntities(Iterable<T> entities) {
        getBaseRepository().deleteInBatch(entities);
    }

    @Override
    public List<T> findAll() {
        return getBaseRepository().findAll();
    }

    @Override
    public List<T> findAll(Pager webPage) {
        Page<T> page = getBaseRepository().findAll(webPage.toPageable());
        webPage.setTotalCount(page.getTotalElements());
        return page.getContent();
    }

    @Override
    public List<T> findByCondition(Specification<T> specification, Pager webPage) {
        Page<T> page = getBaseRepository().findAll(specification, webPage.toPageable());
        webPage.setTotalCount(page.getTotalElements());
        return page.getContent();
    }

    @Override
    public T findByCondition(Specification<T> spec) {
        return getBaseRepository().findOne(spec);
    }

    @Override
    public long count(Specification<T> spec) {
        return getBaseRepository().count(spec);
    }

}
