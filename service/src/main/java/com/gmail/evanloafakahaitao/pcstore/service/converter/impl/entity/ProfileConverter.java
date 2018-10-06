package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ProfileDTO;
import org.springframework.stereotype.Component;

@Component("profileConverter")
public class ProfileConverter implements Converter<ProfileDTO, Profile> {

    @Override
    public Profile toEntity(ProfileDTO dto) {
        Profile profile = new Profile();
        if (dto.getAddress() != null) {
            profile.setAddress(dto.getAddress());
        }
        if (dto.getPhoneNumber() != null) {
            profile.setPhoneNumber(dto.getPhoneNumber());
        }
        return profile;
    }
}
