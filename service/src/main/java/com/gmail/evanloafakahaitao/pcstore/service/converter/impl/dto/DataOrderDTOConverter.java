package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CreateOrderDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dataOrderDTOConverter")
public class DataOrderDTOConverter implements DTOConverter<CreateOrderDTO, Order> {

    private final DTOConverter<SimpleItemDTO, Item> simpleItemDTOConverter;
    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter;

    @Autowired
    public DataOrderDTOConverter(
            @Qualifier("simpleItemDTOConverter") DTOConverter<SimpleItemDTO, Item> simpleItemDTOConverter,
            @Qualifier("simpleUserDTOConverter") DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter
    ) {
        this.simpleItemDTOConverter = simpleItemDTOConverter;
        this.simpleUserDTOConverter = simpleUserDTOConverter;
    }

    @Override
    public CreateOrderDTO toDto(Order entity) {
        CreateOrderDTO order = new CreateOrderDTO();
        order.setQuantity(entity.getQuantity());
        order.setItemVendorCode(entity.getItem().getVendorCode());
        order.setUserEmail(entity.getUser().getEmail());
        return order;
    }
}
