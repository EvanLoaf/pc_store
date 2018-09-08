package com.gmail.evanloafakahaitao.dao;

import com.gmail.evanloafakahaitao.dao.model.User;

public interface UserDao extends GenericDao<User> {

    User findByEmail(String email);
}
