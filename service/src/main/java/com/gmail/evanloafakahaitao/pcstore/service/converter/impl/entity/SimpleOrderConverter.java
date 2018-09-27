package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleOrderDTO;
import org.springframework.stereotype.Component;

@Component
public class SimpleOrderConverter implements Converter<SimpleOrderDTO, Order> {

    private Converter itemConverter = new ItemConverter();

    @SuppressWarnings("unchecked")
    @Override
    public Order toEntity(SimpleOrderDTO dto) {
        return Order.newBuilder()
                .withUuid(dto.getUuid())
                .withCreated(dto.getCreated())
                .withStatus(dto.getStatus())
                .withQuantity(dto.getQuantity())
                .withItem((Item) itemConverter.toEntity(dto.getItem()))
                .build();
    }
}
