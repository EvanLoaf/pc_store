package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.BusinessCard;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.BusinessCardDTO;
import org.springframework.stereotype.Component;

@Component("businessCardDTOConverter")
public class BusinessCardDTOConverter implements DTOConverter<BusinessCardDTO, BusinessCard> {

    @Override
    public BusinessCardDTO toDto(BusinessCard entity) {
        BusinessCardDTO businessCard = new BusinessCardDTO();
        businessCard.setId(entity.getId());
        businessCard.setFullName(entity.getFullName());
        businessCard.setTitle(entity.getTitle());
        businessCard.setWorkingTelephone(entity.getWorkingTelephone());
        return businessCard;
    }
}
