package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleOrderDTO;

public class SimpleOrderDTOConverter implements DTOConverter<SimpleOrderDTO, Order> {

    private DTOConverter itemDTOConverter = new ItemDTOConverter();

    @SuppressWarnings("unchecked")
    @Override
    public SimpleOrderDTO toDto(Order entity) {
        return SimpleOrderDTO.newBuilder()
                .withUuid(entity.getUuid())
                .withCreated(entity.getCreated())
                .withStatus(entity.getStatus())
                .withQuantity(entity.getQuantity())
                .withItem((ItemDTO) itemDTOConverter.toDto(entity.getItem()))
                .build();
    }
}
