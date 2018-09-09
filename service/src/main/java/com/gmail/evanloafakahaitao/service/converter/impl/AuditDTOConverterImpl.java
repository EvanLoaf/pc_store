package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Audit;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.dto.AuditDTO;
import com.gmail.evanloafakahaitao.service.dto.AuditUserDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuditDTOConverterImpl<D, E> implements DTOConverter<AuditDTO, Audit> {

    private DTOConverter auditUserDTOConverter = new AuditUserDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public AuditDTO toDto(Audit entity) {
        return AuditDTO.newBuilder()
                .withId(entity.getId())
                .withEventType(entity.getEventType())
                .withCreated(entity.getCreated())
                .withUser((AuditUserDTO) auditUserDTOConverter.toDto(entity.getUser()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AuditDTO> toDTOList(List<Audit> entityList) {
        return entityList.stream().map(entity -> AuditDTO.newBuilder()
                .withId(entity.getId())
                .withEventType(entity.getEventType())
                .withCreated(entity.getCreated())
                .withUser((AuditUserDTO) auditUserDTOConverter.toDto(entity.getUser()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<AuditDTO> toDTOSet(Set<Audit> entitySet) {
        return null;
    }
}
