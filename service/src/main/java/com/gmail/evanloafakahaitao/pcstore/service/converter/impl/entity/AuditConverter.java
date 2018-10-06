package com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Audit;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.AuditDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("auditConverter")
public class AuditConverter implements Converter<AuditDTO, Audit> {

    private final Converter<SimpleUserDTO, User> simpleUserConverter;

    @Autowired
    public AuditConverter(
            @Qualifier("simpleUserConverter") Converter<SimpleUserDTO, User> simpleUserConverter
    ) {
        this.simpleUserConverter = simpleUserConverter;
    }

    @Override
    public Audit toEntity(AuditDTO dto) {
        Audit audit = new Audit();
        audit.setId(audit.getId());
        audit.setEventType(dto.getEventType());
        audit.setCreated(dto.getCreated());
        audit.setUser(simpleUserConverter.toEntity(dto.getUser()));
        return audit;
    }
}
