package com.gmail.evanloafakahaitao.converter.impl;

import com.gmail.evanloafakahaitao.converter.Converter;
import com.gmail.evanloafakahaitao.dto.ItemDTO;
import com.gmail.evanloafakahaitao.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemConverterImpl<D, E> implements Converter<ItemDTO, Item> {
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
}
