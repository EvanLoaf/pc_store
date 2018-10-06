package com.gmail.evanloafakahaitao.pcstore.service.dto;

import com.gmail.evanloafakahaitao.pcstore.dao.model.PermissionEnum;

import java.io.Serializable;
import java.util.Objects;

public class PermissionDTO implements Serializable {

    private Long id;
    private PermissionEnum name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PermissionEnum getName() {
        return name;
    }

    public void setName(PermissionEnum name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionDTO that = (PermissionDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
