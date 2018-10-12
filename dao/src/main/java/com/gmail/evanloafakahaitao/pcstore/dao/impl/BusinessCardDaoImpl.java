package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.BusinessCardDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.BusinessCard;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessCardDaoImpl extends GenericDaoImpl<BusinessCard> implements BusinessCardDao {

    public BusinessCardDaoImpl() {
        super(BusinessCard.class);
    }
}
