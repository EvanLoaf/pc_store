package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CreateOrderDTO;
import org.springframework.stereotype.Component;

@Component("dataOrderDTOConverter")
public class DataOrderDTOConverter implements DTOConverter<CreateOrderDTO, Order> {

    @Override
    public CreateOrderDTO toDto(Order entity) {
        CreateOrderDTO order = new CreateOrderDTO();
        order.setQuantity(entity.getQuantity());
        order.setItemVendorCode(entity.getItem().getVendorCode());
        order.setUserEmail(entity.getUser().getEmail());
        return order;
    }
}
