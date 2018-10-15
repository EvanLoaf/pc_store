package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DataOrderDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dataOrderConverter")
public class DataOrderConverter implements Converter<DataOrderDTO, Order> {

    private final Converter<SimpleItemDTO, Item> simpleItemConverter;
    private final Converter<SimpleUserDTO, User> simpleUserConverter;

    @Autowired
    public DataOrderConverter(
            @Qualifier("simpleItemConverter") Converter<SimpleItemDTO, Item> simpleItemConverter,
            @Qualifier("simpleUserConverter") Converter<SimpleUserDTO, User> simpleUserConverter
    ) {
        this.simpleItemConverter = simpleItemConverter;
        this.simpleUserConverter = simpleUserConverter;
    }

    @Override
    public Order toEntity(DataOrderDTO dto) {
        Order order = new Order();
        order.setQuantity(dto.getQuantity());
        if (dto.getUuid() != null) {
            order.setUuid(dto.getUuid());
        }
        if (dto.getUser() != null) {
            order.setUser(simpleUserConverter.toEntity(dto.getUser()));
        }
        if (dto.getItem() != null) {
            order.setItem(simpleItemConverter.toEntity(dto.getItem()));
        }
        return order;
    }
}