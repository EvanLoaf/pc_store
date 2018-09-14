package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import org.hibernate.query.Query;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    public UserDaoImpl(Class<User> clazz) {
        super(clazz);
    }

    @Override
    public User findByEmail(String email) {
        String hql = "from User as u where u.email=:email";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }
}
