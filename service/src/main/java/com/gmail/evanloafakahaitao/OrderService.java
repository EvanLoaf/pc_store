package com.gmail.evanloafakahaitao;

import com.gmail.evanloafakahaitao.model.Order;

import java.util.List;

public interface OrderService {

    int save(Long userId, Long vendorCode, int itemQuantity);

    List<Order> findByUserId(Long id);

    int deleteByUuid(String uuid);
}
