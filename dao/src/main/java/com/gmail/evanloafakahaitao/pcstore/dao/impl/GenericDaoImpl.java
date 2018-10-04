package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.GenericDao;
import com.gmail.evanloafakahaitao.pcstore.dao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

    private Class<T> clazz;

    /*@Autowired
    private HibernateUtil hibernateUtil;*/
    /*private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();*/
    @Autowired
    private SessionFactory sessionFactory;

    public GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T findOne(long entityId) {
        return getCurrentSession().get(clazz, entityId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
    }

    @Override
    public void create(T entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }

    //TODO protected postavit
    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
