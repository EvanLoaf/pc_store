package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Profile;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.ProfileDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfileDTOConverterImpl<D, E> implements DTOConverter<ProfileDTO, Profile> {
    @Override
    public ProfileDTO toDto(Profile entity) {
        return ProfileDTO.newBuilder()
                .withUserId(entity.getUserId())
                .withAddress(entity.getAddress())
                .withPhoneNumber(entity.getPhoneNumber())
                .build();
    }

    @Override
    public List<ProfileDTO> toDTOList(List<Profile> entityList) {
        return entityList.stream().map(entity -> ProfileDTO.newBuilder()
                .withUserId(entity.getUserId())
                .withAddress(entity.getAddress())
                .withPhoneNumber(entity.getPhoneNumber())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<ProfileDTO> toDTOSet(Set<Profile> entitySet) {
        return null;
    }
}
