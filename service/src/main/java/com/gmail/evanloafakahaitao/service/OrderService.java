package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.dao.model.Order;

import java.util.List;

public interface OrderService {

    int save(Long userId, Long vendorCode, int itemQuantity);

    List<Order> findByUserId(Long id);

    int deleteByUuid(String uuid);
}
