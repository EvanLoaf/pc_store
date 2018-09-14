package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;

public class SimpleUserConverter implements Converter<SimpleUserDTO, User> {
    @Override
    public User toEntity(SimpleUserDTO dto) {
        return User.newBuilder()
                .withEmail(dto.getEmail())
                .build();
    }
}
