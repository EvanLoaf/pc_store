package com.gmail.evanloafakahaitao.dao;

import com.gmail.evanloafakahaitao.dao.model.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemDao extends GenericDao<Item> {

    Item findByVendorCode(Long vendorCode);
}
