package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {

    DiscountDTO save(DiscountDTO discountDTO);

    DiscountDTO findByPercent(DiscountDTO discountDTO);

    List<DiscountDTO> findAll();
}
