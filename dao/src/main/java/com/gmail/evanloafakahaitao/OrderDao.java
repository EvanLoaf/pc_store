package com.gmail.evanloafakahaitao;

import com.gmail.evanloafakahaitao.model.Item;
import com.gmail.evanloafakahaitao.model.Order;

import java.sql.Connection;
import java.util.List;

public interface OrderDao {

    int save(Connection connection, Order order, Item item);

    List<Order> findByUserId(Connection connection, Long id);

    int deleteByUuid(Connection connection, String uuid);
}
