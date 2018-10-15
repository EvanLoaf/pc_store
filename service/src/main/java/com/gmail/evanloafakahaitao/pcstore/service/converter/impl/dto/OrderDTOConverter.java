package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

@Component("orderDTOConverter")
public class OrderDTOConverter implements DTOConverter<OrderDTO, Order> {

    private final DTOConverter<OrderUserDTO, User> orderUserDTOConverter;
    private final DTOConverter<ItemDTO, Item> itemDTOConverter;

    @Autowired
    public OrderDTOConverter(
            @Qualifier("orderUserDTOConverter") DTOConverter<OrderUserDTO, User> orderUserDTOConverter,
            @Qualifier("itemDTOConverter") DTOConverter<ItemDTO, Item> itemDTOConverter
    ) {
        this.orderUserDTOConverter = orderUserDTOConverter;
        this.itemDTOConverter = itemDTOConverter;
    }

    @Override
    public OrderDTO toDto(Order entity) {
        OrderDTO order = new OrderDTO();
        order.setUuid(entity.getUuid());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        order.setCreated(entity.getCreated().format(formatter));
        order.setStatus(entity.getStatus());
        order.setQuantity(entity.getQuantity());
        order.setTotalPrice(entity.getTotalPrice().setScale(2, RoundingMode.CEILING));
        order.setUser(orderUserDTOConverter.toDto(entity.getUser()));
        order.setItem(itemDTOConverter.toDto(entity.getItem()));
        return order;
    }
}
