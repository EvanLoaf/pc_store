package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;

public class DiscountDTOConverter implements DTOConverter<DiscountDTO, Discount> {

    @Override
    public DiscountDTO toDto(Discount entity) {
        if (entity != null) {
            return DiscountDTO.newBuilder()
                    .withId(entity.getId())
                    .withFinishDate(entity.getFinishDate())
                    .withName(entity.getName())
                    .withPercent(entity.getPercent())
                    .build();
        } else {
            return DiscountDTO.newBuilder()
                    .withPercent(0)
                    .build();
        }
    }
}
