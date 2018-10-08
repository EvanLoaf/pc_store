package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ProfileDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.RoleDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverter implements Converter<UserDTO, User> {

    private final Converter<ProfileDTO, Profile> profileConverter;
    private final Converter<RoleDTO, Role> roleConverter;
    private final Converter<DiscountDTO, Discount> discountConverter;

    @Autowired
    public UserConverter(
            @Qualifier("profileConverter") Converter<ProfileDTO, Profile> profileConverter,
            @Qualifier("roleConverter") Converter<RoleDTO, Role> roleConverter,
            @Qualifier("discountConverter") Converter<DiscountDTO, Discount> discountConverter
    ) {
        this.profileConverter = profileConverter;
        this.roleConverter = roleConverter;
        this.discountConverter = discountConverter;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        if (dto.getId() != null) {
            user.setId(dto.getId());
        }
        if (dto.getDiscount() != null) {
            user.setDiscount(discountConverter.toEntity(dto.getDiscount()));
        }
        if (dto.getRole() != null) {
            user.setRole(roleConverter.toEntity(dto.getRole()));
        }
        if (dto.getProfile() != null) {
            user.setProfile(profileConverter.toEntity(dto.getProfile()));
        }
        if (dto.isDisabled() != null) {
            user.setDisabled(dto.isDisabled());
        }
        return user;
    }
}
