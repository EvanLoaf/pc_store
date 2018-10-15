package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO findByName(RoleDTO roleDTO);

    List<RoleDTO> findAll();
}
