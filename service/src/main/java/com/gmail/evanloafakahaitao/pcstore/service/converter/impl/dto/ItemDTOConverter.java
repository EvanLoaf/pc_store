package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component("itemDTOConverter")
public class ItemDTOConverter implements DTOConverter<ItemDTO, Item> {

    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;

    @Autowired
    public ItemDTOConverter(
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter
    ) {
        this.discountDTOConverter = discountDTOConverter;
    }

    @Override
    public ItemDTO toDto(Item entity) {
        ItemDTO item = new ItemDTO();
        item.setId(entity.getId());
        item.setName(entity.getName());
        item.setVendorCode(entity.getVendorCode());
        item.setDescription(entity.getDescription());
        item.setPrice(entity.getPrice().setScale(2, RoundingMode.CEILING));
        if (!entity.getDiscounts().isEmpty()) {
            item.setDiscounts(discountDTOConverter.toDTOSet(entity.getDiscounts()));
        }
        return item;
    }
}
