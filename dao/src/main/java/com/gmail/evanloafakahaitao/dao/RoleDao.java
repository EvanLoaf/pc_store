package com.gmail.evanloafakahaitao.dao;

import com.gmail.evanloafakahaitao.dao.model.Role;

public interface RoleDao extends GenericDao<Role> {

    Role findByName(String name);
}
