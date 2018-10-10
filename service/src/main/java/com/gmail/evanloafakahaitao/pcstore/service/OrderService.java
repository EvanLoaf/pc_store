package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.*;

import java.util.List;

public interface OrderService {

    SimpleOrderDTO save(DataOrderDTO dataOrderDTO);

    List<SimpleOrderDTO> findByUserId(SimpleUserDTO simpleUserDTO, Integer startPosition, Integer maxResults);

    SimpleOrderDTO findByUuid(DataOrderDTO dataOrderDTO);

    SimpleOrderDTO update(SimpleOrderDTO orderDTO);

    SimpleOrderDTO deleteByUuid(SimpleOrderDTO simpleOrderDTO);

    List<OrderDTO> findAll(Integer startPosition, Integer maxResults);

    Long countByItemId(SimpleItemDTO item);
}
