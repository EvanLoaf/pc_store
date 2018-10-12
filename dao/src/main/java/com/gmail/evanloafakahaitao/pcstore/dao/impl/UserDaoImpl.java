package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        String hql = "from User as u where u.email=:email";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }

    @Override
    public int updateDisable(Long id, boolean isDisabled) {
        String hql = "update User as u set u.isDisabled=:isDisabled where u.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("isDisabled", isDisabled);
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
