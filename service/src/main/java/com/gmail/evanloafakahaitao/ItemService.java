package com.gmail.evanloafakahaitao;

import com.gmail.evanloafakahaitao.model.Item;

import java.util.List;

public interface ItemService {

    int save(List<Item> itemList);

    List<Item> findAll();

    Item findByVendorCode(Long vendorCode);

    Item findById(int itemId);
}
