package com.gmail.evanloafakahaitao.service.converter.impl;

import com.gmail.evanloafakahaitao.dao.model.Audit;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.dto.AuditDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuditConverterImpl implements Converter<AuditDTO, Audit> {

    private Converter auditUserConverter = new AuditUserConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public Audit toEntity(AuditDTO dto) {
        return Audit.newBuilder()
                .withId(dto.getId())
                .withEventType(dto.getEventType())
                .withCreated(dto.getCreated())
                .withUser((User) auditUserConverter.toEntity(dto.getUser()))
                .build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Audit> toEntityList(List<AuditDTO> dtoList) {
        return dtoList.stream().map(dto -> Audit.newBuilder()
                .withId(dto.getId())
                .withEventType(dto.getEventType())
                .withCreated(dto.getCreated())
                .withUser((User) auditUserConverter.toEntity(dto.getUser()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public Set<Audit> toEntitySet(Set<AuditDTO> dtoSet) {
        return null;
    }
}
