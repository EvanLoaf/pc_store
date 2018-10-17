package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Permission;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.PermissionDTO;
import org.springframework.stereotype.Component;

@Component("permissionConverter")
public class PermissionConverter implements Converter<PermissionDTO, Permission> {

    @Override
    public Permission toEntity(PermissionDTO dto) {
        Permission permission = new Permission();
        permission.setName(dto.getName());
        if (dto.getId() != null) {
            permission.setId(dto.getId());
        }
        return permission;
    }
}
