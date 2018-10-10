package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import org.springframework.stereotype.Component;

@Component("simpleItemConverter")
public class SimpleItemConverter implements Converter<SimpleItemDTO, Item> {

    @Override
    public Item toEntity(SimpleItemDTO dto) {
        Item item = new Item();
        if (dto.getId() != null) {
            item.setId(dto.getId());
        }
        if (dto.getName() != null) {
            item.setName(dto.getName());
        }
        item.setVendorCode(dto.getVendorCode());
        return item;
    }
}
