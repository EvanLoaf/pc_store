package com.gmail.evanloafakahaitao.dao.util;

import com.gmail.evanloafakahaitao.dao.model.Item;
import com.gmail.evanloafakahaitao.dao.model.Order;
import com.gmail.evanloafakahaitao.dao.model.OrderId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderConverter {

    private static final Logger logger = LogManager.getLogger(OrderConverter.class);

    public Order toOrder(ResultSet resultSet) {
        OrderId id = null;
        String orderUuid = null;
        LocalDateTime created = null;
        int quantity = 0;
        Long itemId = Long.valueOf(0);
        try {
            //id = resultSet.getLong("order_id");
            orderUuid = resultSet.getString("uuid");
            String createdStr = resultSet.getString("created");
            created = LocalDateTime.parse(createdStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            quantity = resultSet.getInt("quantity");
            itemId = resultSet.getLong("item_id");
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Order.newBuilder()
                .withId(id)
                .withUuid(orderUuid)
                .withQuantity(quantity)
                .withCreated(created)
                .withItem(
                        Item.newBuilder()
                                .withId(itemId)
                                .build()
                )
                .build();
    }
}
