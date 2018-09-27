package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;

public class DiscountConverter implements Converter<DiscountDTO, Discount> {

    @Override
    public Discount toEntity(DiscountDTO dto) {
        if (dto != null) {
            return new Discount(
                    dto.getName(),
                    dto.getPercent(),
                    dto.getFinishDate()
            );
        } else {
            Discount discount = new Discount();
            discount.setPercent(0);
            return discount;
        }
    }
}
