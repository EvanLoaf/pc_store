package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.AuditUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.RoleDTO;

public class AuditUserDTOConverter implements DTOConverter<AuditUserDTO, User> {

    private DTOConverter roleDTOConverter = new RoleDTOConverter();

    @SuppressWarnings("unchecked")
    @Override
    public AuditUserDTO toDto(User entity) {
        return AuditUserDTO.newBuilder()
                .withName(entity.getFirstName() + " " + entity.getLastName())
                .withEmail(entity.getEmail())
                .withRole((RoleDTO) roleDTOConverter.toDto(entity.getRole()))
                .build();
    }
}
