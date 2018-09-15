package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;

public interface DiscountService {

    DiscountDTO save(DiscountDTO discountDTO);

    DiscountDTO findByPercent(DiscountDTO discountDTO);
}
