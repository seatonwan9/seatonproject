package com.xearth.sp.seatonproject.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author wangxudong
 * @date 2020/10/26 11:27
 */
public interface BaseService<T, ID> {

    List<T> findAll();

    List<T> findAll(Sort sort);

    T findById(ID id);

    Page<T> findAllByPageSize(Pageable pageable);

    void save(T entity);

    void saveAll(Iterable<T> entities);

    void delete(T entity);

    void deleteById(ID id);

    void deleteAll(Iterable<T> entities);

}
