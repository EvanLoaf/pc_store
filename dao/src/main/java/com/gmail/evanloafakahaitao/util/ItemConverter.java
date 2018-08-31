package com.gmail.evanloafakahaitao.util;


import com.gmail.evanloafakahaitao.model.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemConverter {

    public Item toItem(ResultSet resultSet) {
        String name = null;
        Long vendorCode = null;
        String description = null;
        BigDecimal price = null;
        Integer id = null;
        try {
            if (resultSet.getString("item_id") != null) {
                id = resultSet.getInt("item_id");
            }
            name = resultSet.getString("item_name");
            vendorCode = resultSet.getLong("vendor_code");
            description = resultSet.getString("description");
            price = resultSet.getBigDecimal("price");
        } catch (SQLException e) {
            System.out.println("Error extracting values from result set into ItemConverter");
            e.printStackTrace();
        }
        return Item.newBuilder()
                .withId(id)
                .withName(name)
                .withVendorCode(vendorCode)
                .withDescription(description)
                .withPrice(price)
                .build();
    }
}
