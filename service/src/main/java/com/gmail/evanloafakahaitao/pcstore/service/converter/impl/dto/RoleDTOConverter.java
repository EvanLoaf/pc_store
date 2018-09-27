package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.RoleDTO;
import org.springframework.stereotype.Component;

@Component("roleDTOConverter")
public class RoleDTOConverter implements DTOConverter<RoleDTO, Role> {

    private DTOConverter permissionDTOConverter = new PermissionDTOConverter();

    @SuppressWarnings("unchecked")
    @Override
    public RoleDTO toDto(Role entity) {
        return RoleDTO.newBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withPermissionSet(permissionDTOConverter.toDTOSet(entity.getPermissionSet()))
                .build();
    }
}
