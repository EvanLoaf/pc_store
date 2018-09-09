package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Permission;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.PermissionDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissionConverterImpl implements Converter<PermissionDTO, Permission> {
    @Override
    public Permission toEntity(PermissionDTO dto) {
        return Permission.newBuilder()
                .withId(dto.getId())
                .withName(dto.getName())
                .build();
    }

    @Override
    public List<Permission> toEntityList(List<PermissionDTO> dtoList) {
        return dtoList.stream().map(dto -> Permission.newBuilder()
                .withId(dto.getId())
                .withName(dto.getName())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<Permission> toEntitySet(Set<PermissionDTO> dtoSet) {
        return dtoSet.stream().map(dto -> Permission.newBuilder()
                .withId(dto.getId())
                .withName(dto.getName())
                .build()).collect(Collectors.toSet());
    }
}
