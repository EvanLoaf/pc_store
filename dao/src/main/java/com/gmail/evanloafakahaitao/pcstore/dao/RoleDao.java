package com.gmail.evanloafakahaitao.pcstore.dao;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Role;

public interface RoleDao extends GenericDao<Role> {

    Role findByName(String name);

    Role findDefault();
}
