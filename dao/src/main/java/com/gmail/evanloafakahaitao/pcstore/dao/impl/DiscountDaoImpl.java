package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.DiscountDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements DiscountDao {

    public DiscountDaoImpl() {
        super(Discount.class);
    }
}
