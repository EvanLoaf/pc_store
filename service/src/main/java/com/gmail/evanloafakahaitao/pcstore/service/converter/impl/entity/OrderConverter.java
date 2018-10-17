package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderConverter")
public class OrderConverter implements Converter<OrderDTO, Order> {

    private final Converter<OrderUserDTO, User> orderUserConverter;
    private final Converter<ItemDTO, Item> itemConverter;

    @Autowired
    public OrderConverter(
            @Qualifier("orderUserConverter") Converter<OrderUserDTO, User> orderUserConverter,
            @Qualifier("itemConverter") Converter<ItemDTO, Item> itemConverter
    ) {
        this.orderUserConverter = orderUserConverter;
        this.itemConverter = itemConverter;
    }

    @Override
    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getTotalPrice());
        order.setUser(orderUserConverter.toEntity(dto.getUser()));
        order.setItem(itemConverter.toEntity(dto.getItem()));
        if (dto.getUuid() != null) {
            order.setUuid(dto.getUuid());
        }
        if (dto.getStatus() != null) {
            order.setStatus(dto.getStatus());
        }
        return order;
    }
}
