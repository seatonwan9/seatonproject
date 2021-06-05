package com.xearth.sp.seatonproject.dao.impl;

import com.xearth.sp.seatonproject.dao.BaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Iterator;

/**
 * @author wangxudong
 * @date 2020/9/16 17:26
 */
@Transactional
@SuppressWarnings("SpringJavaConstructorAutowiringInspection")
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private static final int BATCH_SIZE = 20000;

    private EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public <S extends T> Iterable<S> batchInsert(Iterable<S> var1) {
        Iterator<S> iterator = var1.iterator();
        int index = 0;
        while (iterator.hasNext()){
            entityManager.persist(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0){
                entityManager.flush();
                entityManager.clear();
            }
        }
        if (index % BATCH_SIZE != 0){
            entityManager.flush();
            entityManager.clear();
        }
        return var1;
    }

    @Override
    public <S extends T> Iterable<S> batchUpdate(Iterable<S> var1) {
        Iterator<S> iterator = var1.iterator();
        int index = 0;
        while (iterator.hasNext()){
            entityManager.merge(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0){
                entityManager.flush();
                entityManager.clear();
            }
        }
        if (index % BATCH_SIZE != 0){
            entityManager.flush();
            entityManager.clear();
        }
        return var1;
    }
}
