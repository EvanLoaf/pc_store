package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ProfileDTO;

public class OrderUserDTOConverter implements DTOConverter<OrderUserDTO, User> {

    private DTOConverter profileDTOConverter = new ProfileDTOConverter();
    private DTOConverter discountDTOConverter = new DiscountDTOConverter();

    @SuppressWarnings("unchecked")
    @Override
    public OrderUserDTO toDto(User entity) {
        return OrderUserDTO.newBuilder()
                .withName(entity.getFirstName() + " " + entity.getLastName())
                .withEmail(entity.getEmail())
                .withProfile((ProfileDTO) profileDTOConverter.toDto(entity.getProfile()))
                .withDiscount((DiscountDTO) discountDTOConverter.toDto(entity.getDiscount()))
                .build();
    }
}
