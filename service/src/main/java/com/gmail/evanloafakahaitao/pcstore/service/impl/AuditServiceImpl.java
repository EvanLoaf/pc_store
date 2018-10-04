package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.AuditDao;
import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.AuditDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Audit;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.AuditService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.Converter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.AuditDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.entity.AuditUserConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.AuditDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {

    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);

    private final AuditDao auditDao;
    private final UserDao userDao;
    private final Converter auditConverter;
    private final DTOConverter auditDTOConverter;

    @Autowired
    public AuditServiceImpl(
            AuditDao auditDao,
            UserDao userDao,
            @Qualifier("auditConverter") Converter auditConverter,
            @Qualifier("auditDTOConverter") DTOConverter auditDTOConverter
    ) {
        this.auditDao = auditDao;
        this.userDao = userDao;
        this.auditConverter = auditConverter;
        this.auditDTOConverter = auditDTOConverter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public AuditDTO save(AuditDTO auditDTO) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Audit audit = (Audit) auditConverter.toEntity(auditDTO);
            User user = userDao.findByEmail(auditDTO.getUser().getEmail());
            audit.setUser(user);
            auditDao.create(audit);
            AuditDTO auditDTOsaved = (AuditDTO) auditDTOConverter.toDto(audit);
            transaction.commit();
            return auditDTOsaved;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to save Audit", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AuditDTO> findAll() {
        Session session = auditDao.getCurrentSession();
        List<AuditDTO> auditDTOS = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Audit> audits = auditDao.findAll();
            auditDTOS = auditDTOConverter.toDTOList(audits);
            transaction.commit();
            return auditDTOS;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Audit", e);
        }
        return auditDTOS;
    }

    @Override
    public boolean deleteById(AuditDTO auditDTO) {
        Session session = auditDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            auditDao.deleteById(auditDTO.getId());
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to delete audit by id", e);
        }
        return false;
    }
}
