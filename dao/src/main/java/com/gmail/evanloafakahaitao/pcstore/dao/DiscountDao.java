package com.gmail.evanloafakahaitao.pcstore.dao;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Discount;

public interface DiscountDao extends GenericDao<Discount> {

    Discount findByPercent(Integer percent);
}
