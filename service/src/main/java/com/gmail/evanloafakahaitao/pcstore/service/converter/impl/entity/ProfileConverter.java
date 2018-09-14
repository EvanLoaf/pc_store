package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ProfileDTO;

public class ProfileConverter implements Converter<ProfileDTO, Profile> {
    @Override
    public Profile toEntity(ProfileDTO dto) {
        return Profile.newBuilder()
                .withAddress(dto.getAddress())
                .withPhoneNumber(dto.getPhoneNumber())
                .build();
    }
}
