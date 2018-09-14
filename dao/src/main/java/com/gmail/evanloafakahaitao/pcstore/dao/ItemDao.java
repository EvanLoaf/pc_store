package com.gmail.evanloafakahaitao.pcstore.dao;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;

public interface ItemDao extends GenericDao<Item> {

    Item findByVendorCode(String vendorCode);
}
