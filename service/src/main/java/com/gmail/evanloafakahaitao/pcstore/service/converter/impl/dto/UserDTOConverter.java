package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.*;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userDTOConverter")
public class UserDTOConverter implements DTOConverter<UserDTO, User> {

    private final DTOConverter<RoleDTO, Role> roleDTOConverter;
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;
    private final DTOConverter<BusinessCardDTO, BusinessCard> businessCardDTOConverter;

    @Autowired
    public UserDTOConverter(
            @Qualifier("roleDTOConverter") DTOConverter<RoleDTO, Role> roleDTOConverter,
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter,
            @Qualifier("businessCardDTOConverter") DTOConverter<BusinessCardDTO, BusinessCard> businessCardDTOConverter
    ) {
        this.roleDTOConverter = roleDTOConverter;
        this.discountDTOConverter = discountDTOConverter;
        this.businessCardDTOConverter = businessCardDTOConverter;
    }

    @Override
    public UserDTO toDto(User entity) {
        UserDTO user = new UserDTO();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setRole(roleDTOConverter.toDto(entity.getRole()));
        if (entity.getProfile() != null && entity.getProfile().getPhoneNumber() != null) {
            user.setAddress(entity.getProfile().getAddress());
            user.setPhoneNumber(entity.getProfile().getPhoneNumber());
        }
        user.setDisabled(entity.isDisabled());
        if (entity.getDiscount() != null) {
            user.setDiscount(discountDTOConverter.toDto(entity.getDiscount()));
        }
        if (!entity.getBusinessCards().isEmpty()) {
            user.setBusinessCards(businessCardDTOConverter.toDTOSet(entity.getBusinessCards()));
        }
        return user;
    }
}
