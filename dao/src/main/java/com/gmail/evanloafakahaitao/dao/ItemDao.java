package com.gmail.evanloafakahaitao.dao;

import com.gmail.evanloafakahaitao.dao.model.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemDao extends GenericDao<Item> {

    int save(Connection connection, List<Item> itemList);

    List<Item> findAll(Connection connection);

    Item findByVendorCode(Connection connection, Long vendorCode);

    Item findById(Connection connection, Long itemId);
}
