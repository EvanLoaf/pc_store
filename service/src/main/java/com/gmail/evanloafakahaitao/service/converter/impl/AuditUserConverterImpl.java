package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Role;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.AuditUserDTO;

import java.util.List;
import java.util.Set;

public class AuditUserConverterImpl<D, E> implements Converter<AuditUserDTO, User> {

    private Converter roleConverter = new RoleConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public User toEntity(AuditUserDTO dto) {
        return User.newBuilder()
                .withEmail(dto.getEmail())
                .withRole((Role) roleConverter.toEntity(dto.getRole()))
                .build();
    }

    @Override
    public List<User> toEntityList(List<AuditUserDTO> dtoList) {
        return null;
    }

    @Override
    public Set<User> toEntitySet(Set<AuditUserDTO> dtoSet) {
        return null;
    }
}
