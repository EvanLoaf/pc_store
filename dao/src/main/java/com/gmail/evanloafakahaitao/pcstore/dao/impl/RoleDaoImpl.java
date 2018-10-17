package com.gmail.evanloafakahaitao.pcstore.dao.impl;

import com.gmail.evanloafakahaitao.pcstore.dao.RoleDao;
import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(String name) {
        String hql = "from Role as r where r.name=:name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        return (Role) query.uniqueResult();
    }

    @Override
    public Role findDefault() {
        String hql = "from Role as r where r.isDefault=true";
        Query query = getCurrentSession().createQuery(hql);
        return (Role) query.uniqueResult();
    }
}
