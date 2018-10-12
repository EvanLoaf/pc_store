package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.AuditDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Audit;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.AuditService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.AuditDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
public class AuditServiceImpl implements AuditService {

    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);

    private final AuditDao auditDao;
    private final UserDao userDao;
    private final Converter<AuditDTO, Audit> auditConverter;
    private final DTOConverter<AuditDTO, Audit> auditDTOConverter;

    @Autowired
    public AuditServiceImpl(
            AuditDao auditDao,
            UserDao userDao,
            @Qualifier("auditConverter") Converter<AuditDTO, Audit> auditConverter,
            @Qualifier("auditDTOConverter") DTOConverter<AuditDTO, Audit> auditDTOConverter
    ) {
        this.auditDao = auditDao;
        this.userDao = userDao;
        this.auditConverter = auditConverter;
        this.auditDTOConverter = auditDTOConverter;
    }

    @Override
    public AuditDTO save(AuditDTO auditDTO) {
        logger.info("Saving Audit");
        if (auditDTO.getUser().getEmail() != null) {
            Audit audit = auditConverter.toEntity(auditDTO);
            if (audit.getCreated() == null) {
                audit.setCreated(LocalDateTime.now());
            }
            User user = userDao.findByEmail(auditDTO.getUser().getEmail());
            audit.setUser(user);
            auditDao.create(audit);
            return auditDTOConverter.toDto(audit);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditDTO> findAll(Integer startPosition, Integer maxResults) {
        logger.info("Retrieving all Audit");
        List<Audit> audits = auditDao.findAll(startPosition, maxResults);
        return auditDTOConverter.toDTOList(audits);
    }

    @Override
    public AuditDTO deleteById(AuditDTO auditDTO) {
        logger.info("Deleting Audit by Id");
        auditDao.deleteById(auditDTO.getId());
        return auditDTO;
    }
}
