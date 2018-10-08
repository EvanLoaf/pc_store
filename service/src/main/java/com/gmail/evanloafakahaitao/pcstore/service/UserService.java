package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleUserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    List<UserDTO> findAll(Integer startPosition, Integer maxResults);

    UserDTO update(UserDTO userDTO);

    SimpleUserDTO findByEmail(SimpleUserDTO userDTO);
}
