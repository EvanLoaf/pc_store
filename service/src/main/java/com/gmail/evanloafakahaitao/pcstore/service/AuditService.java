package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.AuditDTO;

import java.util.List;

public interface AuditService {

    AuditDTO save(AuditDTO auditDTO);

    List<AuditDTO> findAll();

    boolean deleteById(AuditDTO auditDTO);
}
