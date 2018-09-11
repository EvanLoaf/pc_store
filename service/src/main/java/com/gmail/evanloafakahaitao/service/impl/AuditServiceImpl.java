package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.AuditDao;
import com.gmail.evanloafakahaitao.dao.UserDao;
import com.gmail.evanloafakahaitao.dao.impl.AuditDaoImpl;
import com.gmail.evanloafakahaitao.dao.impl.UserDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.Audit;
import com.gmail.evanloafakahaitao.dao.model.User;
import com.gmail.evanloafakahaitao.service.AuditService;
import com.gmail.evanloafakahaitao.service.converter.Converter;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.converter.impl.AuditDTOConverterImpl;
import com.gmail.evanloafakahaitao.service.converter.impl.AuditUserConverterImpl;
import com.gmail.evanloafakahaitao.service.dto.AuditDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AuditServiceImpl implements AuditService {

    private static final Logger logger = LogManager.getLogger(AuditServiceImpl.class);

    private AuditDao auditDao = new AuditDaoImpl(Audit.class);
    private UserDao userDao = new UserDaoImpl(User.class);
    private Converter auditConverter = new AuditUserConverterImpl();
    private DTOConverter auditDTOConverter = new AuditDTOConverterImpl();

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
            transaction.commit();
            return (AuditDTO) auditDTOConverter.toDto(audit);
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
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            List<Audit> auditList = auditDao.findAll();
            List<AuditDTO> auditDTOList = auditDTOConverter.toDTOList(auditList);
            transaction.commit();
            return auditDTOList;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to retrieve Audit", e);
        }
        return null;
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
