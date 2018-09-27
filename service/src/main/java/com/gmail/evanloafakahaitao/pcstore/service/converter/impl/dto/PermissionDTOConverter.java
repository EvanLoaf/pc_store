package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Permission;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.PermissionDTO;
import org.springframework.stereotype.Component;

@Component
public class PermissionDTOConverter implements DTOConverter<PermissionDTO, Permission> {
    @Override
    public PermissionDTO toDto(Permission entity) {
        return PermissionDTO.newBuilder()
                .withId(entity.getId())
                .withName(entity.getName())
                .build();
    }
}
