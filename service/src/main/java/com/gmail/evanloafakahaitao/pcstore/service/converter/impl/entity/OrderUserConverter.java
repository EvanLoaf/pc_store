package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderUserConverter")
public class OrderUserConverter implements Converter<OrderUserDTO, User> {

    private final Converter<DiscountDTO, Discount> discountConverter;

    @Autowired
    public OrderUserConverter(
            @Qualifier("discountConverter") Converter<DiscountDTO, Discount> discountConverter
    ) {
        this.discountConverter = discountConverter;
    }

    @Override
    public User toEntity(OrderUserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        Profile profile = new Profile();
        if (dto.getAddress() != null) {
            profile.setAddress(dto.getAddress());
        }
        if (dto.getPhoneNumber() != null) {
            profile.setPhoneNumber(dto.getPhoneNumber());
        }
        user.setProfile(profile);
        if (dto.getDiscount() != null) {
            user.setDiscount(discountConverter.toEntity(dto.getDiscount()));
        }
        return user;
    }
}
