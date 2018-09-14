package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderUserDTO;

public class OrderUserConverter implements Converter<OrderUserDTO, User> {

    private Converter profileConverter = new ProfileConverter();

    @SuppressWarnings("unchecked")
    @Override
    public User toEntity(OrderUserDTO dto) {
        return User.newBuilder()
                .withEmail(dto.getEmail())
                .withProfile((Profile) profileConverter.toEntity(dto.getProfile()))
                .build();
    }
}
