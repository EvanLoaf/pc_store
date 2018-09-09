package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.dao.model.Item;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemConverterImpl implements Converter<ItemDTO, Item> {
    @Override
    public Item toEntity(ItemDTO dto) {
        return Item.newBuilder()
                .withDescription(dto.getDescription())
                .withId(dto.getId())
                .withName(dto.getName())
                .withPrice(dto.getPrice())
                .withVendorCode(dto.getVendorCode())
                .build();
    }

    @Override
    public List<Item> toEntityList(List<ItemDTO> dtoList) {
        return dtoList.stream().map(dto -> Item.newBuilder()
                .withDescription(dto.getDescription())
                .withId(dto.getId())
                .withName(dto.getName())
                .withPrice(dto.getPrice())
                .withVendorCode(dto.getVendorCode())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<Item> toEntitySet(Set<ItemDTO> dtoSet) {
        return null;
    }
}
