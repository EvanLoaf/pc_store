package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderUserConverter")
public class OrderUserConverter implements Converter<OrderUserDTO, User> {

    private final Converter<ProfileDTO, Profile> profileConverter;
    private final Converter<DiscountDTO, Discount> discountConverter;

    @Autowired
    public OrderUserConverter(
            @Qualifier("profileConverter") Converter<ProfileDTO, Profile> profileConverter,
            @Qualifier("discountConverter") Converter<DiscountDTO, Discount> discountConverter
    ) {
        this.profileConverter = profileConverter;
        this.discountConverter = discountConverter;
    }

    @Override
    public User toEntity(OrderUserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        if (dto.getProfile() != null) {
            user.setProfile(profileConverter.toEntity(dto.getProfile()));
        }
        if (dto.getDiscount() != null) {
            user.setDiscount(discountConverter.toEntity(dto.getDiscount()));
        }
        return user;
    }
}
