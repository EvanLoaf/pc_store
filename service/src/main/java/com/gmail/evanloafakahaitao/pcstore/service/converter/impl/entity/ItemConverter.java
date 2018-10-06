package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("itemConverter")
public class ItemConverter implements Converter<ItemDTO, Item> {

    private final Converter<DiscountDTO, Discount> discountConverter;

    @Autowired
    public ItemConverter(
            @Qualifier("discountConverter") Converter<DiscountDTO, Discount> discountConverter
    ) {
        this.discountConverter = discountConverter;
    }

    @Override
    public Item toEntity(ItemDTO dto) {
        Item item = new Item();
        item.setVendorCode(dto.getVendorCode());
        if (dto.getName() != null) {
            item.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            item.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            item.setPrice(dto.getPrice());
        }
        if (dto.getId() != null) {
            item.setId(dto.getId());
        }
        if (!dto.getDiscounts().isEmpty()) {
            item.setDiscounts(discountConverter.toEntitySet(dto.getDiscounts()));
        }
        return item;
    }
}
