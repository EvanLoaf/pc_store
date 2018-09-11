package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.service.dto.AuditDTO;

import java.util.List;

public interface AuditService {

    AuditDTO save(AuditDTO auditDTO);

    List<AuditDTO> findAll();

    boolean deleteById(AuditDTO auditDTO);
}
