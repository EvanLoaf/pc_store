package com.gmail.evanloafakahaitao.dao;

import com.gmail.evanloafakahaitao.dao.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List findByUserId(Long id);

    int deleteByUuid(String uuid);
}
