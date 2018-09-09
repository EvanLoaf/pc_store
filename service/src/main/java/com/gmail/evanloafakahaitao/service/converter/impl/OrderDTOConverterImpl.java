package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Order;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.service.dto.OrderDTO;
import com.gmail.evanloafakahaitao.service.dto.OrderUserDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderDTOConverterImpl<D, E> implements DTOConverter<OrderDTO, Order> {

    private DTOConverter orderUserDTOConverter = new OrderUserDTOConverterImpl();
    private DTOConverter itemDTOConverter = new ItemDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public OrderDTO toDto(Order entity) {
        return OrderDTO.newBuilder()
                .withUuid(entity.getUuid())
                .withCreated(entity.getCreated())
                .withStatus(entity.getStatus())
                .withQuantity(entity.getQuantity())
                .withItem((ItemDTO) itemDTOConverter.toDto(entity.getItem()))
                .withUser((OrderUserDTO) orderUserDTOConverter.toDto(entity.getUser()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderDTO> toDTOList(List<Order> entityList) {
        return entityList.stream().map(entity -> OrderDTO.newBuilder()
                .withUuid(entity.getUuid())
                .withCreated(entity.getCreated())
                .withStatus(entity.getStatus())
                .withQuantity(entity.getQuantity())
                .withItem((ItemDTO) itemDTOConverter.toDto(entity.getItem()))
                .withUser((OrderUserDTO) orderUserDTOConverter.toDto(entity.getUser()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<OrderDTO> toDTOSet(Set<Order> entitySet) {
        return null;
    }
}
