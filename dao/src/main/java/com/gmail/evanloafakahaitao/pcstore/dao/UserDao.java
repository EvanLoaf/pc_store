package com.gmail.evanloafakahaitao.pcstore.dao;

import com.gmail.evanloafakahaitao.pcstore.dao.model.User;

public interface UserDao extends GenericDao<User> {

    User findByEmail(String email);

    int updateDisable(Long id, boolean isDisabled);
}
