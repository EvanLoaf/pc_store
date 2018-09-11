package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Role;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.RoleDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleConverterImpl implements Converter<RoleDTO, Role> {

    private Converter permissionConverter = new PermissionConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public Role toEntity(RoleDTO dto) {
        return Role.newBuilder()
                .withId(dto.getId())
                .withName(dto.getName())
                .withPermissionSet(permissionConverter.toEntitySet(dto.getPermissionSet()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> toEntityList(List<RoleDTO> dtoList) {
        return dtoList.stream().map(dto -> Role.newBuilder()
                .withId(dto.getId())
                .withName(dto.getName())
                .withPermissionSet(permissionConverter.toEntitySet(dto.getPermissionSet()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<Role> toEntitySet(Set<RoleDTO> dtoSet) {
        return null;
    }
}
