package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import org.springframework.stereotype.Component;

@Component("simpleUserDTOConverter")
public class SimpleUserDTOConverter implements DTOConverter<SimpleUserDTO, User> {

    @Override
    public SimpleUserDTO toDto(User entity) {
        SimpleUserDTO user = new SimpleUserDTO();
        if (entity != null) {
            user.setId(entity.getId());
            user.setEmail(entity.getEmail());
            user.setName(entity.getFirstName() + " " + entity.getLastName());
        }
        return user;
    }
}
