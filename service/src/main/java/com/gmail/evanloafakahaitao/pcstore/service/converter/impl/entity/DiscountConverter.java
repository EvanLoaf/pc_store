package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import org.springframework.stereotype.Component;

@Component("discountConverter")
public class DiscountConverter implements Converter<DiscountDTO, Discount> {

    @Override
    public Discount toEntity(DiscountDTO dto) {
        Discount discount = new Discount();
        discount.setName(discount.getName());
        discount.setPercent(dto.getPercent());
        discount.setFinishDate(dto.getFinishDate());
        if (discount.getId() != null) {
            discount.setId(dto.getId());
        }
        return discount;
    }
}
