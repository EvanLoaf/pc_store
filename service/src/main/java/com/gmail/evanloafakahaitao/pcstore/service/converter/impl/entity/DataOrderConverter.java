package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CreateOrderDTO;
import org.springframework.stereotype.Component;

@Component("dataOrderConverter")
public class DataOrderConverter implements Converter<CreateOrderDTO, Order> {

    @Override
    public Order toEntity(CreateOrderDTO dto) {
        Order order = new Order();
        order.setQuantity(dto.getQuantity());
        User user = new User();
        user.setEmail(dto.getUserEmail());
        order.setUser(user);
        Item item = new Item();
        item.setVendorCode(dto.getItemVendorCode());
        order.setItem(item);
        return order;
    }
}
