package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.AuditDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Audit;

public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {

    public AuditDaoImpl(Class<Audit> clazz) {
        super(clazz);
    }
}
