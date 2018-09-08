package com.gmail.evanloafakahaitao.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RoleDTO implements Serializable {

    private Long id;
    private String name;
    private Set<PermissionDTO> permissionSet = new HashSet<>();

    public RoleDTO() {
    }

    private RoleDTO(Builder builder) {
        id = builder.id;
        setName(builder.name);
        setPermissionSet(builder.permissionSet);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PermissionDTO> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<PermissionDTO> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public static final class Builder {
        private Long id;
        private String name;
        private Set<PermissionDTO> permissionSet;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withPermissionSet(Set<PermissionDTO> val) {
            permissionSet = val;
            return this;
        }

        public RoleDTO build() {
            return new RoleDTO(this);
        }
    }
}
