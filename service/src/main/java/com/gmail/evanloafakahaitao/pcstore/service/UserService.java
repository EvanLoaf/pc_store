package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DiscountDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    SimpleUserDTO findByEmail(SimpleUserDTO simpleUserDTO);

    UserDTO findById(UserDTO userDTO);

    SimpleUserDTO deleteById(SimpleUserDTO simpleUserDTO);

    Long countAll();

    List<UserDTO> findAllNotDeleted(Integer startPosition, Integer maxResults);

    DiscountDTO updateDiscountAll(Long discountId);
}
