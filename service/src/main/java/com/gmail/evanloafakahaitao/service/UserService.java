package com.gmail.evanloafakahaitao.service;

import com.gmail.evanloafakahaitao.dao.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByEmail(String email);
}
