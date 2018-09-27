package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.AuditDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Audit;
import org.springframework.stereotype.Repository;

@Repository
public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {

    public AuditDaoImpl() {
        super(Audit.class);
    }
}
