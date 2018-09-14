package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.AuditUserDTO;

public class AuditUserConverter implements Converter<AuditUserDTO, User> {

    private Converter roleConverter = new RoleConverter();

    @SuppressWarnings("unchecked")
    @Override
    public User toEntity(AuditUserDTO dto) {
        return User.newBuilder()
                .withEmail(dto.getEmail())
                .withRole((Role) roleConverter.toEntity(dto.getRole()))
                .build();
    }
}
