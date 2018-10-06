package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import org.springframework.stereotype.Component;

@Component("discountDTOConverter")
public class DiscountDTOConverter implements DTOConverter<DiscountDTO, Discount> {

    @Override
    public DiscountDTO toDto(Discount entity) {
        DiscountDTO discount = new DiscountDTO();
        discount.setId(entity.getId());
        discount.setName(entity.getName());
        discount.setPercent(entity.getPercent());
        discount.setFinishDate(entity.getFinishDate());
        return discount;
    }
}
