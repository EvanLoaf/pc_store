package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Role;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.RoleDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleDTOConverterImpl<D, E> implements DTOConverter<RoleDTO, Role> {

    private DTOConverter permissionDTOConverter = new PermissionDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public RoleDTO toDto(Role entity) {
        return RoleDTO.newBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withPermissionSet(permissionDTOConverter.toDTOSet(entity.getPermissionSet()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RoleDTO> toDTOList(List<Role> entityList) {
        return entityList.stream().map(entity -> RoleDTO.newBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withPermissionSet(permissionDTOConverter.toDTOSet(entity.getPermissionSet()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<RoleDTO> toDTOSet(Set<Role> entitySet) {
        return null;
    }
}
