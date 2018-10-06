package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Permission;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.PermissionDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("roleConverter")
public class RoleConverter implements Converter<RoleDTO, Role> {

    private final Converter<PermissionDTO, Permission> permissionConverter;

    @Autowired
    public RoleConverter(
            @Qualifier("permissionConverter") Converter<PermissionDTO, Permission> permissionConverter
    ) {
        this.permissionConverter = permissionConverter;
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setName(dto.getName());
        if (dto.getId() != null) {
            role.setId(dto.getId());
        }
        if (!dto.getPermissions().isEmpty()) {
            role.setPermissions(permissionConverter.toEntitySet(dto.getPermissions()));
        }
        return role;
    }
}
