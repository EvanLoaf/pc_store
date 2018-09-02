package com.gmail.evanloafakahaitao.util;


import com.gmail.evanloafakahaitao.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemConverter {

    private static final Logger logger = LogManager.getLogger(ItemConverter.class);

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
            logger.error(e.getMessage(), e);
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
