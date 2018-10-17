package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    SimpleUserDTO findByEmail(String email);

    UserDTO findById(Long id);

    UserDTO findByCurrentId();

    void deleteById(Long id);

    Long countAllNotDeleted();

    List<UserDTO> findAllNotDeleted(Integer startPosition, Integer maxResults);

    DiscountDTO updateDiscountAll(Long discountId);
}
