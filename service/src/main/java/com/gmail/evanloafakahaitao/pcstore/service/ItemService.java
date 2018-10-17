package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {

    List<ItemDTO> findAllNotDeleted(Integer startPosition, Integer maxResults);

    ItemDTO findByVendorCode(String vendorCode);

    ItemDTO findById(Long id);

    ItemDTO save(ItemDTO itemDTO);

    ItemDTO update(ItemDTO itemDTO);

    void softDelete(Long id);

    void hardDelete(Long id);

    SimpleItemDTO copy(Long id);

    Long countAll();

    DiscountDTO updateDiscountAll(Long discountId, BigDecimal minPriceRange, BigDecimal maxPriceRange);

    void deleteByOrdersCount(Long id);
}
