package com.gmail.evanloafakahaitao.dao;

import com.gmail.evanloafakahaitao.dao.model.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao extends GenericDao<User> {

    List<User> findAll(Connection connection);

    User findByEmail(Connection connection, String email);
}
