package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;

public class UserConverter implements Converter<UserDTO, User> {

    private Converter profileConverter = new ProfileConverter();
    private Converter roleConverter = new RoleConverter();

    @SuppressWarnings("unchecked")
    @Override
    public User toEntity(UserDTO dto) {
        return User.newBuilder()
                .withId(dto.getId())
                .withFirstName(dto.getFirstName())
                .withLastName(dto.getLastName())
                .withEmail(dto.getEmail())
                .withPassword(dto.getPassword())
                .withProfile((Profile) profileConverter.toEntity(dto.getProfile()))
                .withRole((Role) roleConverter.toEntity(dto.getRole()))
                .build();
    }
}
