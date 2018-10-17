package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.*;

import java.util.List;

public interface OrderService {

    SimpleOrderDTO save(DataOrderDTO dataOrderDTO);

    List<SimpleOrderDTO> findByCurrentUserId(Integer startPosition, Integer maxResults);

    SimpleOrderDTO update(SimpleOrderDTO orderDTO);

    void deleteByUuid(String uuid);

    List<OrderDTO> findAll(Integer startPosition, Integer maxResults);

    Long countByItemId(Long itemId);
}
