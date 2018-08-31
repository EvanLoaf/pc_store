package com.gmail.evanloafakahaitao;

import com.gmail.evanloafakahaitao.model.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {

    List<User> findAll(Connection connection);

    User findByEmail(Connection connection, String email);
}
