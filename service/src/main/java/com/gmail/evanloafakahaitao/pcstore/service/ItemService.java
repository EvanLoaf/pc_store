package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {

    List<ItemDTO> findAllNotDeleted(Integer startPosition, Integer maxResults);

    ItemDTO findByVendorCode(ItemDTO itemDTO);

    ItemDTO findById(ItemDTO itemDTO);

    ItemDTO save(ItemDTO itemDTO);

    ItemDTO update(ItemDTO itemDTO);

    SimpleItemDTO softDelete(SimpleItemDTO simpleItemDTO);

    SimpleItemDTO hardDelete(SimpleItemDTO simpleItemDTO);

    SimpleItemDTO copy(SimpleItemDTO simpleItemDTO);

    Long countAll();

    DiscountDTO updateDiscountAll(Long discountId, BigDecimal minPriceRange, BigDecimal maxPriceRange);
}
