package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverter implements Converter<UserDTO, User> {

    private final Converter<DiscountDTO, Discount> discountConverter;

    @Autowired
    public UserConverter(
            @Qualifier("discountConverter") Converter<DiscountDTO, Discount> discountConverter
    ) {
        this.discountConverter = discountConverter;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        if (dto.getDiscount() != null) {
            user.setDiscount(discountConverter.toEntity(dto.getDiscount()));
        }
        if (dto.getDisabled() != null) {
            user.setDisabled(dto.getDisabled());
        }
        return user;
    }
}
