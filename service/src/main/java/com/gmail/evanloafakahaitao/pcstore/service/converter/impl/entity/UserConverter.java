package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;

public class UserConverter implements Converter<UserDTO, User> {

    private Converter profileConverter = new ProfileConverter();
    private Converter roleConverter = new RoleConverter();
    private Converter discountConverter = new DiscountConverter();

    @SuppressWarnings("unchecked")
    @Override
    public User toEntity(UserDTO dto) {
        if (dto.getRole() != null) {
            return User.newBuilder()
                    .withId(dto.getId())
                    .withFirstName(dto.getFirstName())
                    .withLastName(dto.getLastName())
                    .withEmail(dto.getEmail())
                    .withPassword(dto.getPassword())
                    .withProfile((Profile) profileConverter.toEntity(dto.getProfile()))
                    .withRole((Role) roleConverter.toEntity(dto.getRole()))
                    .withDiscount((Discount) discountConverter.toEntity(dto.getDiscount()))
                    .build();
        } else {
            return User.newBuilder()
                    .withId(dto.getId())
                    .withFirstName(dto.getFirstName())
                    .withLastName(dto.getLastName())
                    .withEmail(dto.getEmail())
                    .withPassword(dto.getPassword())
                    .withProfile((Profile) profileConverter.toEntity(dto.getProfile()))
                    .withDiscount((Discount) discountConverter.toEntity(dto.getDiscount()))
                    .build();
        }
    }
}
