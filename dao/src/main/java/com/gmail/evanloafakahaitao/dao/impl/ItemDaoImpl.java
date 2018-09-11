package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.ItemDao;
import com.gmail.evanloafakahaitao.dao.model.Item;
import org.hibernate.query.Query;

public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    public ItemDaoImpl(Class<Item> clazz) {
        super(clazz);
    }

    @Override
    public Item findByVendorCode(Long vendorCode) {
        String hql = "from Item as I where I.vendorCode=:vendorCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("vendorCode", vendorCode);
        return (Item) query.getSingleResult();
    }
}
