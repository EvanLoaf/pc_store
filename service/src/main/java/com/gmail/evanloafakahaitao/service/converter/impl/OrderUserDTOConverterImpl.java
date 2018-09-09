package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.OrderUserDTO;
import com.gmail.evanloafakahaitao.service.dto.ProfileDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderUserDTOConverterImpl<D, E> implements DTOConverter<OrderUserDTO, User> {

    private DTOConverter profileDTOConverter = new ProfileDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public OrderUserDTO toDto(User entity) {
        return OrderUserDTO.newBuilder()
                .withName(entity.getFirstName() + " " + entity.getLastName())
                .withEmail(entity.getEmail())
                .withProfile((ProfileDTO) profileDTOConverter.toDto(entity.getProfile()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderUserDTO> toDTOList(List<User> entityList) {
        return entityList.stream().map(entity -> OrderUserDTO.newBuilder()
                .withName(entity.getFirstName() + " " + entity.getLastName())
                .withEmail(entity.getEmail())
                .withProfile((ProfileDTO) profileDTOConverter.toDto(entity.getProfile()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<OrderUserDTO> toDTOSet(Set<User> entitySet) {
        return null;
    }
}
