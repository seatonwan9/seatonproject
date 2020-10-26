package com.xearth.sp.seatonproject.base.impl;

import com.xearth.sp.seatonproject.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangxudong
 * @date 2020/10/26 11:30
 */
@Transactional
public class BaseServiceImpl<D extends JpaRepositoryImplementation<T, ID>, T, ID> implements BaseService<T, ID> {

    @Autowired
    protected D dao;

    @Override
    public List findAll() {
        return dao.findAll();
    }

    @Override
    public List findAll(Sort sort) {
        return dao.findAll(sort);
    }

    @Override
    public T findById(ID id) {
        return (T) dao.findById(id);
    }

    @Override
    public Page findAllByPageSize(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public void save(T entity) {
        dao.save(entity);
    }

    @Override
    public void saveAll(Iterable<T> entities) {
        dao.saveAll(entities);
    }

    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteAll(Iterable<T> entities) {
        dao.deleteAll(entities);
    }
}
