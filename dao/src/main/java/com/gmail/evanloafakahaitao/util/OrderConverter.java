package com.gmail.evanloafakahaitao.util;

import com.gmail.evanloafakahaitao.model.Item;
import com.gmail.evanloafakahaitao.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderConverter {

    public Order toOrder(ResultSet resultSet) {
        Long id = null;
        String orderUuid = null;
        String createdDate = null;
        int quantity = 0;
        int itemId = 0;
        try {
            id = resultSet.getLong("order_id");
            orderUuid = resultSet.getString("uuid");
            createdDate = resultSet.getString("created");
            quantity = resultSet.getInt("quantity");
            itemId = resultSet.getInt("item_id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Order.newBuilder()
                .withId(id)
                .withOrderUuid(orderUuid)
                .withQuantity(quantity)
                .withCreatedDate(createdDate)
                .withItem(
                        Item.newBuilder()
                                .withId(itemId)
                                .build()
                )
                .build();
    }
}
