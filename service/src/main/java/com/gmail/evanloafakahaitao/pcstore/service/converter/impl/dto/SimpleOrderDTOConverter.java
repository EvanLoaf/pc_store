package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component("simpleOrderDTOConverter")
public class SimpleOrderDTOConverter implements DTOConverter<SimpleOrderDTO, Order> {

    private final DTOConverter<SimpleItemDTO, Item> simpleItemDTOConverter;

    @Autowired
    public SimpleOrderDTOConverter(
            @Qualifier("simpleItemDTOConverter") DTOConverter<SimpleItemDTO, Item> simpleItemDTOConverter
    ) {
        this.simpleItemDTOConverter = simpleItemDTOConverter;
    }

    @Override
    public SimpleOrderDTO toDto(Order entity) {
        SimpleOrderDTO order = new SimpleOrderDTO();
        order.setCreated(entity.getCreated());
        order.setStatus(entity.getStatus());
        order.setQuantity(entity.getQuantity());
        order.setTotalPrice(entity.getTotalPrice().setScale(2, RoundingMode.CEILING));
        order.setUuid(entity.getUuid());
        order.setItem(simpleItemDTOConverter.toDto(entity.getItem()));
        return order;
    }
}
