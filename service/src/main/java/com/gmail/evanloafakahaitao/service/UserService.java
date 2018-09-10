package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findByEmail(String email);
}
