package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Profile;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.ProfileDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfileConverterImpl<D, E> implements Converter<ProfileDTO, Profile> {
    @Override
    public Profile toEntity(ProfileDTO dto) {
        return Profile.newBuilder()
                .withUserId(dto.getUserId())
                .withAddress(dto.getAddress())
                .withPhoneNumber(dto.getPhoneNumber())
                .build();
    }

    @Override
    public List<Profile> toEntityList(List<ProfileDTO> dtoList) {
        return dtoList.stream().map(dto -> Profile.newBuilder()
                .withUserId(dto.getUserId())
                .withAddress(dto.getAddress())
                .withPhoneNumber(dto.getPhoneNumber())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<Profile> toEntitySet(Set<ProfileDTO> dtoSet) {
        return null;
    }
}
