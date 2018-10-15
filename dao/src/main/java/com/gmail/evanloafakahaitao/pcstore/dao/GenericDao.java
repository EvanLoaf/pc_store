package com.gmail.evanloafakahaitao.pcstore.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {

    T findOne(final long entityId);

    List<T> findAll(Integer startPosition, Integer maxResults);

    List<T> findAll();

    Long countAll();

    void create(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

    //TODO deleted method from interface to make it protected..
    //Session getCurrentSession();
}
