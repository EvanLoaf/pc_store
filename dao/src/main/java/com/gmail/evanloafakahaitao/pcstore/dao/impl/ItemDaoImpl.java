package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.ItemDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import org.hibernate.query.Query;

public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {

    public ItemDaoImpl(Class<Item> clazz) {
        super(clazz);
    }

    @Override
    public Item findByVendorCode(String vendorCode) {
        String hql = "from Item as i where i.vendorCode=:vendorCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("vendorCode", vendorCode);
        return (Item) query.getSingleResult();
    }
}
