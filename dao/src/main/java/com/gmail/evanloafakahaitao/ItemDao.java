package com.gmail.evanloafakahaitao;

import com.gmail.evanloafakahaitao.model.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemDao {

    int save(Connection connection, List<Item> itemList);

    List<Item> findAll(Connection connection);

    Item findByVendorCode(Connection connection, Long vendorCode);

    Item findById(Connection connection, Long itemId);
}
