package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Order;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.service.dto.ShowToUserOrderDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShowToUserOrderDTOConverterImpl<D, E> implements DTOConverter<ShowToUserOrderDTO, Order> {

    private DTOConverter itemDTOConverter = new ItemDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public ShowToUserOrderDTO toDto(Order entity) {
        return ShowToUserOrderDTO.newBuilder()
                .withUuid(entity.getUuid())
                .withCreated(entity.getCreated())
                .withStatus(entity.getStatus())
                .withQuantity(entity.getQuantity())
                .withItem((ItemDTO) itemDTOConverter.toDto(entity.getItem()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ShowToUserOrderDTO> toDTOList(List<Order> entityList) {
        return entityList.stream().map(entity -> ShowToUserOrderDTO.newBuilder()
                .withUuid(entity.getUuid())
                .withCreated(entity.getCreated())
                .withStatus(entity.getStatus())
                .withQuantity(entity.getQuantity())
                .withItem((ItemDTO) itemDTOConverter.toDto(entity.getItem()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<ShowToUserOrderDTO> toDTOSet(Set<Order> entitySet) {
        return null;
    }
}
