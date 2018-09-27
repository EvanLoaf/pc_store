package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ProfileDTO;
import org.springframework.stereotype.Component;

@Component
public class ProfileDTOConverter implements DTOConverter<ProfileDTO, Profile> {
    @Override
    public ProfileDTO toDto(Profile entity) {
        return ProfileDTO.newBuilder()
                .withAddress(entity.getAddress())
                .withPhoneNumber(entity.getPhoneNumber())
                .build();
    }
}
