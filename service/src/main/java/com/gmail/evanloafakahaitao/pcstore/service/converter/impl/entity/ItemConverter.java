package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;

public class ItemConverter implements Converter<ItemDTO, Item> {

    private Converter discountConverter = new DiscountConverter();

    @SuppressWarnings("unchecked")
    @Override
    public Item toEntity(ItemDTO dto) {
        return Item.newBuilder()
                .withDescription(dto.getDescription())
                .withId(dto.getId())
                .withName(dto.getName())
                .withPrice(dto.getPrice())
                .withVendorCode(dto.getVendorCode())
                .withDiscounts(discountConverter.toEntitySet(dto.getDiscounts()))
                .build();
    }
}
