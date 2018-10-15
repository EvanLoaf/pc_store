package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleOrderDTO;
import net.sf.ehcache.search.expression.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("simpleOrderConverter")
public class SimpleOrderConverter implements Converter<SimpleOrderDTO, Order> {

    private final Converter<SimpleItemDTO, Item> simpleItemConverter;

    @Autowired
    public SimpleOrderConverter(
            @Qualifier("simpleItemConverter") Converter<SimpleItemDTO, Item> simpleItemConverter
    ) {
        this.simpleItemConverter = simpleItemConverter;
    }

    @Override
    public Order toEntity(SimpleOrderDTO dto) {
        //TODO might be probs here, not sure yet
        Order order = new Order();
        order.setUuid(dto.getUuid());
        order.setStatus(dto.getStatus());
        order.setTotalPrice(dto.getTotalPrice());
        order.setQuantity(dto.getQuantity());
        order.setItem(simpleItemConverter.toEntity(dto.getItem()));
        return order;
    }
}
