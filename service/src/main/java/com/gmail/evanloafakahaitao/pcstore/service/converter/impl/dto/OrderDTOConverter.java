package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderUserDTO;

public class OrderDTOConverter implements DTOConverter<OrderDTO, Order> {

    private DTOConverter orderUserDTOConverter = new OrderUserDTOConverter();
    private DTOConverter itemDTOConverter = new ItemDTOConverter();

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
}
