package com.gmail.evanloafakahaitao.service.impl;

import com.gmail.evanloafakahaitao.dao.RoleDao;
import com.gmail.evanloafakahaitao.dao.impl.RoleDaoImpl;
import com.gmail.evanloafakahaitao.dao.model.Role;
import com.gmail.evanloafakahaitao.service.RoleService;
import com.gmail.evanloafakahaitao.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.service.converter.impl.RoleDTOConverterImpl;
import com.gmail.evanloafakahaitao.service.dto.RoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private RoleDao roleDao = new RoleDaoImpl(Role.class);
    private DTOConverter roleDTOConverter = new RoleDTOConverterImpl();

    @SuppressWarnings("unchecked")
    @Override
    public RoleDTO findByName(RoleDTO roleDTO) {
        Session session = roleDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            Role role = roleDao.findByName(roleDTO.getName());
            RoleDTO foundRoleDTO = (RoleDTO) roleDTOConverter.toDto(role);
            transaction.commit();
            return foundRoleDTO;
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Failed to find Role by name", e);
        }
        return null;
    }
}
