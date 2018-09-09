package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Permission;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.PermissionDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissionDTOConverterImpl implements DTOConverter<PermissionDTO, Permission> {
    @Override
    public PermissionDTO toDto(Permission entity) {
        return PermissionDTO.newBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .build();
    }

    @Override
    public List<PermissionDTO> toDTOList(List<Permission> entityList) {
        return entityList.stream().map(entity -> PermissionDTO.newBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<PermissionDTO> toDTOSet(Set<Permission> entitySet) {
        return entitySet.stream().map(entity -> PermissionDTO.newBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .build()).collect(Collectors.toSet());
    }
}
