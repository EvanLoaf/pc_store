package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Profile;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("orderUserDTOConverter")
public class OrderUserDTOConverter implements DTOConverter<OrderUserDTO, User> {

    private final DTOConverter<ProfileDTO, Profile> profileDTOConverter;
    private final DTOConverter<DiscountDTO, Discount> discountDTOConverter;

    @Autowired
    public OrderUserDTOConverter(
            @Qualifier("profileDTOConverter") DTOConverter<ProfileDTO, Profile> profileDTOConverter,
            @Qualifier("discountDTOConverter") DTOConverter<DiscountDTO, Discount> discountDTOConverter
    ) {
        this.profileDTOConverter = profileDTOConverter;
        this.discountDTOConverter = discountDTOConverter;
    }

    @Override
    public OrderUserDTO toDto(User entity) {
        OrderUserDTO user = new OrderUserDTO();
        user.setEmail(entity.getEmail());
        user.setName(entity.getFirstName() + " " + entity.getLastName());
        user.setProfile(profileDTOConverter.toDto(entity.getProfile()));
        if (entity.getDiscount() != null) {
            user.setDiscount(discountDTOConverter.toDto(entity.getDiscount()));
        }
        return user;
    }
}
