package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.AuditDao;
import com.gmail.evanloafakahaitao.dao.model.Audit;

public class AuditDaoImpl extends GenericDaoImpl<Audit> implements AuditDao {

    public AuditDaoImpl(Class<Audit> clazz) {
        super(clazz);
    }
}
