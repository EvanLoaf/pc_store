package com.gmail.evanloafakahaitao.dao;

import com.gmail.evanloafakahaitao.dao.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> findByUserId(Long id);

    Order findByUuid(String uuid);

    int deleteByUuid(String uuid);
}
