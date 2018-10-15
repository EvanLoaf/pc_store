package com.gmail.evanloafakahaitao.pcstore.dao;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemDao extends GenericDao<Item> {

    Item findByVendorCode(String vendorCode);

    List<Item> findInPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    List<Item> findByDiscount(Integer percent);

    Long findCountInPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    int softDelete(Long id);

    List<Item> findAllNotDeleted(Integer startPosition, Integer maxResults);
}
