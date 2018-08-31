package com.gmail.evanloafakahaitao;

import com.gmail.evanloafakahaitao.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByEmail(String email);
}
