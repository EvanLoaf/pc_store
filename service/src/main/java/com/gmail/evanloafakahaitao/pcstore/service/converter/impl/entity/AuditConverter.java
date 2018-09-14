package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Audit;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.AuditDTO;

public class AuditConverter implements Converter<AuditDTO, Audit> {

    private Converter auditUserConverter = new AuditUserConverter();

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
}
