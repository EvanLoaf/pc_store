package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Audit;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.AuditDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("auditDTOConverter")
public class AuditDTOConverter implements DTOConverter<AuditDTO, Audit> {

    private final DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter;

    @Autowired
    public AuditDTOConverter(
            @Qualifier("simpleUserDTOConverter") DTOConverter<SimpleUserDTO, User> simpleUserDTOConverter
    ) {
        this.simpleUserDTOConverter = simpleUserDTOConverter;
    }

    @Override
    public AuditDTO toDto(Audit entity) {
        AuditDTO audit = new AuditDTO();
        audit.setId(entity.getId());
        audit.setEventType(entity.getEventType());
        audit.setCreated(entity.getCreated());
        audit.setUser(simpleUserDTOConverter.toDto(entity.getUser()));
        return audit;
    }
}
