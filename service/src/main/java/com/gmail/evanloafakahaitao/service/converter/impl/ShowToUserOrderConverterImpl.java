package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Item;
import com.gmail.evanloafakahaitao.dao.model.Order;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.ShowToUserOrderDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShowToUserOrderConverterImpl implements Converter<ShowToUserOrderDTO, Order> {

    private Converter itemConverter = new ItemConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public Order toEntity(ShowToUserOrderDTO dto) {
        return Order.newBuilder()
                .withUuid(dto.getUuid())
                .withCreated(dto.getCreated())
                .withStatus(dto.getStatus())
                .withQuantity(dto.getQuantity())
                .withItem((Item) itemConverter.toEntity(dto.getItem()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> toEntityList(List<ShowToUserOrderDTO> dtoList) {
        return dtoList.stream().map(dto -> Order.newBuilder()
                .withUuid(dto.getUuid())
                .withCreated(dto.getCreated())
                .withStatus(dto.getStatus())
                .withQuantity(dto.getQuantity())
                .withItem((Item) itemConverter.toEntity(dto.getItem()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<Order> toEntitySet(Set<ShowToUserOrderDTO> dtoSet) {
        return null;
    }
}
