package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import org.springframework.stereotype.Component;

@Component("simpleItemDTOConverter")
public class SimpleItemDTOConverter implements DTOConverter<SimpleItemDTO, Item> {

    @Override
    public SimpleItemDTO toDto(Item entity) {
        SimpleItemDTO item = new SimpleItemDTO();
        if (entity != null) {
            item.setId(entity.getId());
            item.setVendorCode(entity.getVendorCode());
            item.setName(entity.getName());
        }
        return item;
    }
}
