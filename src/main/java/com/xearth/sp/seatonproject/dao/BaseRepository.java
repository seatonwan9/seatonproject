package com.xearth.sp.seatonproject.dao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author wangxudong
 * @date 2020/9/16 17:23
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepositoryImplementation<T, ID> {

    <S extends T> Iterable<S> batchInsert(Iterable<S> var1);

    <S extends T> Iterable<S> batchUpdate(Iterable<S> var1);

}
