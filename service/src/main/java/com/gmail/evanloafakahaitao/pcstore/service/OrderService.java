package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.OrderDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleOrderDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;

import java.util.List;

public interface OrderService {

    SimpleOrderDTO save(OrderDTO orderDTO);

    List<SimpleOrderDTO> findByUserId(UserDTO userDTO);

    SimpleOrderDTO findByUuid(OrderDTO orderDTO);

    OrderDTO update(OrderDTO orderDTO);

    Integer deleteByUuid(OrderDTO orderDTO);

    List<OrderDTO> findAll();
}
