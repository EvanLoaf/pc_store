package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.RoleDTO;

public class RoleConverter implements Converter<RoleDTO, Role> {

    private Converter permissionConverter = new PermissionConverter();

    @SuppressWarnings("unchecked")
    @Override
    public Role toEntity(RoleDTO dto) {
        return Role.newBuilder()
                .withId(dto.getId())
                .withName(dto.getName())
                .withPermissionSet(permissionConverter.toEntitySet(dto.getPermissionSet()))
                .build();
    }
}
