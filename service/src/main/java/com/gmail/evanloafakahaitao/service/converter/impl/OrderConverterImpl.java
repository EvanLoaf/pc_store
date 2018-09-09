package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Item;
import com.gmail.evanloafakahaitao.dao.model.Order;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.OrderDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderConverterImpl<D, E> implements Converter<OrderDTO, Order> {

    private Converter orderUserConverter = new OrderUserConverterImpl();
    private Converter itemConverter = new ItemConverterImpl();

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

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> toEntityList(List<OrderDTO> dtoList) {
        return dtoList.stream().map(dto -> Order.newBuilder()
                .withUuid(dto.getUuid())
                .withCreated(dto.getCreated())
                .withStatus(dto.getStatus())
                .withQuantity(dto.getQuantity())
                .withItem((Item) itemConverter.toEntity(dto.getItem()))
                .withUser((User) orderUserConverter.toEntity(dto.getUser()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<Order> toEntitySet(Set<OrderDTO> dtoSet) {
        return null;
    }
}
