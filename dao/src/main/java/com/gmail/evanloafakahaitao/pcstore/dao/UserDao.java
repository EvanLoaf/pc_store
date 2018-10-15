package com.gmail.evanloafakahaitao.pcstore.dao;

import com.gmail.evanloafakahaitao.pcstore.dao.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {

    User findByEmail(String email);

    List<User> findAllNotDeleted(Integer startPosition, Integer maxResults);
}
