package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.service.dto.CommentFeedbackNewsLoginUserDTO;
import com.gmail.evanloafakahaitao.service.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO update(UserDTO userDTO);

    CommentFeedbackNewsLoginUserDTO findByEmail(CommentFeedbackNewsLoginUserDTO userDTO);
}
