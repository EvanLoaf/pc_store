package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.BusinessCard;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.BusinessCardDTO;
import org.springframework.stereotype.Component;

@Component("businessCardConverter")
public class BusinessCardConverter implements Converter<BusinessCardDTO, BusinessCard> {

    @Override
    public BusinessCard toEntity(BusinessCardDTO dto) {
        BusinessCard businessCard = new BusinessCard();
        businessCard.setTitle(dto.getTitle());
        businessCard.setFullName(dto.getFullName());
        businessCard.setWorkingTelephone(dto.getWorkingTelephone());
        return businessCard;
    }
}
