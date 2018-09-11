package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.OrderDao;
import com.gmail.evanloafakahaitao.dao.model.Order;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> findByUserId(Long id) {
        String hql = "from Order as O where O.user.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Order findByUuid(String uuid) {
        String hql = "from Order as O where O.uuid=:uuid";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("uuid", uuid);
        return (Order) query.getSingleResult();
    }

    @Override
    public int deleteByUuid(String uuid) {
        String hql = "delete from Order as O where O.uuid=:uuid";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("uuid", uuid);
        return query.executeUpdate();
    }
}
