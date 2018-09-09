package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Profile;
import com.gmail.evanloafakahaitao.dao.model.Role;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.UserDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserConverterImpl<D, E> implements Converter<UserDTO, User> {

    private Converter profileConverter = new ProfileConverterImpl();
    private Converter roleConverter = new RoleConverterImpl();

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

    @SuppressWarnings("unchecked")
    @Override
    public List<User> toEntityList(List<UserDTO> dtoList) {
        return dtoList.stream().map(dto -> User.newBuilder()
                .withId(dto.getId())
                .withLastName(dto.getLastName())
                .withFirstName(dto.getFirstName())
                .withPassword(dto.getPassword())
                .withEmail(dto.getEmail())
                .withProfile((Profile) profileConverter.toEntity(dto.getProfile()))
                .withRole((Role) roleConverter.toEntity(dto.getRole()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<User> toEntitySet(Set<UserDTO> dtoSet) {
        return null;
    }
}
