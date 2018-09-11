package com.gmail.evanloafakahaitao.dao.impl;

import com.gmail.evanloafakahaitao.dao.RoleDao;
import com.gmail.evanloafakahaitao.dao.model.Role;
import org.hibernate.query.Query;

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    public RoleDaoImpl(Class<Role> clazz) {
        super(clazz);
    }

    @Override
    public Role findByName(String name) {
        String hql = "from Role as R where R.name=:name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }
}
