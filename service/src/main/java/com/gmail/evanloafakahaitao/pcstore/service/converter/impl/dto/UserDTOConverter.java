package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ProfileDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.RoleDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userDTOConverter")
public class UserDTOConverter implements DTOConverter<UserDTO, User> {

    private final DTOConverter<ProfileDTO, Profile> profileDTOConverter;
    private final DTOConverter<RoleDTO, Role> roleDTOConverter;
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;

    @Autowired
    public UserDTOConverter(
            @Qualifier("profileDTOConverter") DTOConverter<ProfileDTO, Profile> profileDTOConverter,
            @Qualifier("roleDTOConverter") DTOConverter<RoleDTO, Role> roleDTOConverter,
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter
    ) {
        this.profileDTOConverter = profileDTOConverter;
        this.roleDTOConverter = roleDTOConverter;
        this.discountDTOConverter = discountDTOConverter;
    }

    @Override
    public UserDTO toDto(User entity) {
        UserDTO user = new UserDTO();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setRole(roleDTOConverter.toDto(entity.getRole()));
        user.setProfile(profileDTOConverter.toDto(entity.getProfile()));
        if (entity.getDiscount() != null) {
            user.setDiscount(discountDTOConverter.toDto(entity.getDiscount()));
        }
        return user;
    }
}
