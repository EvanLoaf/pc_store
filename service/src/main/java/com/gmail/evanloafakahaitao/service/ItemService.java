package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.service.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> save(List<ItemDTO> itemList);

    List<ItemDTO> findAll();

    ItemDTO findByVendorCode(ItemDTO itemDTO);

    ItemDTO findById(ItemDTO itemDTO);

    ItemDTO save(ItemDTO itemDTO);
}
