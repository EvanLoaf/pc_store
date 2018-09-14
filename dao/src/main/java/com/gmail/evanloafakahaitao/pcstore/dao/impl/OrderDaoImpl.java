package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.OrderDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> findByUserId(Long id) {
        String hql = "from Order as o where o.user.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Order findByUuid(String uuid) {
        String hql = "from Order as o where o.uuid=:uuid";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("uuid", uuid);
        return (Order) query.getSingleResult();
    }

    @Override
    public int deleteByUuid(String uuid) {
        String hql = "delete from Order as o where o.uuid=:uuid";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("uuid", uuid);
        return query.executeUpdate();
    }
}
