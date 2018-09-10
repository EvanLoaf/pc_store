package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.service.dto.OrderDTO;
import com.gmail.evanloafakahaitao.service.dto.ShowToUserOrderDTO;
import com.gmail.evanloafakahaitao.service.dto.UserDTO;

import java.util.List;

public interface OrderService {

    OrderDTO save(OrderDTO orderDTO);

    List<ShowToUserOrderDTO> findByUserId(UserDTO userDTO);

    Integer deleteByUuid(OrderDTO orderDTO);

    List<OrderDTO> findAll();
}
