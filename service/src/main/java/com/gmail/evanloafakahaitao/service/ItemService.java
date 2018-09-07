package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.web.dto.ItemDTO;
import com.gmail.evanloafakahaitao.dao.model.Item;

import java.util.List;

public interface ItemService {

    int save(List<Item> itemList);

    List<Item> findAll();

    Item findByVendorCode(Long vendorCode);

    Item findById(Long itemId);

    ItemDTO save(ItemDTO itemDTO);
}
