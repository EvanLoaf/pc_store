package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {

    List<ItemDTO> save(List<ItemDTO> itemList);

    List<ItemDTO> findAll();

    ItemDTO findByVendorCode(ItemDTO itemDTO);

    ItemDTO findById(ItemDTO itemDTO);

    ItemDTO save(ItemDTO itemDTO);

    List<ItemDTO> findInPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Integer startPos, Integer maxResults);

    ItemDTO updateDiscount(ItemDTO itemDTO);

    List<ItemDTO> findByDiscount(DiscountDTO discountDTO);

    Long findCountInPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
}
