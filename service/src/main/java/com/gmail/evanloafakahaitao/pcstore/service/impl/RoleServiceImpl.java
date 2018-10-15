package com.gmail.evanloafakahaitao.pcstore.service.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.RoleDao;
import com.gmail.evanloafakahaitao.pcstore.dao.impl.RoleDaoImpl;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import com.gmail.evanloafakahaitao.pcstore.service.RoleService;
import com.gmail.evanloafakahaitao.pcstore.service.converter.DTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.converter.impl.dto.RoleDTOConverter;
import com.gmail.evanloafakahaitao.pcstore.service.dto.RoleDTO;
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

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    private final RoleDao roleDao;
    private final DTOConverter<RoleDTO, Role> roleDTOConverter;

    @Autowired
    public RoleServiceImpl(
            RoleDao roleDao,
            @Qualifier("roleDTOConverter") DTOConverter<RoleDTO, Role> roleDTOConverter
    ) {
        this.roleDao = roleDao;
        this.roleDTOConverter = roleDTOConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
       logger.info("Retrieving all Roles");
       List<Role> roles = roleDao.findAll();
       return roleDTOConverter.toDTOList(roles);
    }
}
