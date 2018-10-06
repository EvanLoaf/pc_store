package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ProfileDTO;
import org.springframework.stereotype.Component;

@Component("profileDTOConverter")
public class ProfileDTOConverter implements DTOConverter<ProfileDTO, Profile> {

    @Override
    public ProfileDTO toDto(Profile entity) {
        ProfileDTO profile = new ProfileDTO();
        if (entity.getAddress() != null) {
            profile.setAddress(entity.getAddress());
        }
        if (entity.getPhoneNumber() != null) {
            profile.setPhoneNumber(entity.getPhoneNumber());
        }
        return profile;
    }
}
