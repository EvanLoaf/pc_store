package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;

public class ItemDTOConverter implements DTOConverter<ItemDTO, Item> {
    @Override
    public ItemDTO toDto(Item entity) {
        return ItemDTO.newBuilder()
                .withDescription(entity.getDescription())
                .withId(entity.getId())
                .withName(entity.getName())
                .withPrice(entity.getPrice())
                .withVendorCode(entity.getVendorCode())
                .build();
    }
}