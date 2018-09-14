package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderDTO;

public class OrderConverter implements Converter<OrderDTO, Order> {

    private Converter orderUserConverter = new OrderUserConverter();
    private Converter itemConverter = new ItemConverter();

    @SuppressWarnings("unchecked")
    @Override
    public Order toEntity(OrderDTO dto) {
        return Order.newBuilder()
                .withUuid(dto.getUuid())
                .withCreated(dto.getCreated())
                .withStatus(dto.getStatus())
                .withQuantity(dto.getQuantity())
                .withItem((Item) itemConverter.toEntity(dto.getItem()))
                .withUser((User) orderUserConverter.toEntity(dto.getUser()))
                .build();
    }
}
