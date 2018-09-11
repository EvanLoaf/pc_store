package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Profile;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.OrderUserDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderUserConverterImpl implements Converter<OrderUserDTO, User> {

    private Converter profileConverter = new ProfileConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public User toEntity(OrderUserDTO dto) {
        return User.newBuilder()
                .withEmail(dto.getEmail())
                .withProfile((Profile) profileConverter.toEntity(dto.getProfile()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> toEntityList(List<OrderUserDTO> dtoList) {
        return dtoList.stream().map(dto -> User.newBuilder()
                .withEmail(dto.getEmail())
                .withProfile((Profile) profileConverter.toEntity(dto.getProfile()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<User> toEntitySet(Set<OrderUserDTO> dtoSet) {
        return null;
    }
}
