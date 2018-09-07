package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.web.dto.ItemDTO;
import com.gmail.evanloafakahaitao.dao.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemDTOConverterImpl<D, E> implements DTOConverter<ItemDTO, Item> {
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

    @Override
    public List<ItemDTO> toDTOList(List<Item> entityList) {
        return entityList.stream().map(entity -> ItemDTO.newBuilder()
                .withDescription(entity.getDescription())
                .withId(entity.getId())
                .withName(entity.getName())
                .withPrice(entity.getPrice())
                .withVendorCode(entity.getVendorCode())
                .build()).collect(Collectors.toList());
    }
}
