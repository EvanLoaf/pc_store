package com.gmail.evanloafakahaitao.pcstore.dao;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {

    List<Order> findByUserId(Long id, Integer startPosition, Integer maxResults);

    Order findByUuid(String uuid);

    int deleteByUuid(String uuid);

    Long countByItemId(Long id);
}
