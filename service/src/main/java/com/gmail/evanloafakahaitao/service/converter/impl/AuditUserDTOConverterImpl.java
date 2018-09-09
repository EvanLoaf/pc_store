package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.AuditUserDTO;
import com.gmail.evanloafakahaitao.service.dto.RoleDTO;

import java.util.List;
import java.util.Set;

public class AuditUserDTOConverterImpl<D, E> implements DTOConverter<AuditUserDTO, User> {

    private DTOConverter roleDTOConverter = new RoleDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public AuditUserDTO toDto(User entity) {
        return AuditUserDTO.newBuilder()
                .withName(entity.getFirstName() + " " + entity.getLastName())
                .withEmail(entity.getEmail())
                .withRole((RoleDTO) roleDTOConverter.toDto(entity.getRole()))
                .build();
    }

    @Override
    public List<AuditUserDTO> toDTOList(List<User> entityList) {
        return null;
    }

    @Override
    public Set<AuditUserDTO> toDTOSet(Set<User> entitySet) {
        return null;
    }
}
