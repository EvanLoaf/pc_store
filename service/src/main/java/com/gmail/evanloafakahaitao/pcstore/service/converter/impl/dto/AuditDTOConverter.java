package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Audit;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.AuditDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.AuditUserDTO;

public class AuditDTOConverter implements DTOConverter<AuditDTO, Audit> {

    private DTOConverter auditUserDTOConverter = new AuditUserDTOConverter();

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
}
