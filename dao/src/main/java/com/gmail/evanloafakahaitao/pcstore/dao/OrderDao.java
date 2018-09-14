package com.gmail.evanloafakahaitao.pcstore.dao;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> findByUserId(Long id);

    Order findByUuid(String uuid);

    int deleteByUuid(String uuid);
}
