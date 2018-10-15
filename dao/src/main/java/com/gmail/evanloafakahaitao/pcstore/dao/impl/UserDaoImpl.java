package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.UserDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAllNotDeleted(Integer startPosition, Integer maxResults) {
        String hql = "from User as u where u.isDeleted=false";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }
}
