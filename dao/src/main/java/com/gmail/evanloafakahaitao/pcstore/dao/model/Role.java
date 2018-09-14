package com.gmail.evanloafakahaitao.pcstore.dao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "RolePermission",
            joinColumns = @JoinColumn(name = "roleId", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "permissionId", nullable = false, updatable = false)
    )
    private Set<Permission> permissionSet = new HashSet<>();

    public Role() {
    }

    private Role(Builder builder) {
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

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public static final class Builder {
        private Long id;
        private @NotNull String name;
        private Set<Permission> permissionSet = new HashSet<>();

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withName(@NotNull String val) {
            name = val;
            return this;
        }

        public Builder withPermissionSet(Set<Permission> val) {
            permissionSet = val;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
