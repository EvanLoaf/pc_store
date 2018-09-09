package com.gmail.evanloafakahaitao.dao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "T_ROLE_PERMISSION",
            joinColumns = @JoinColumn(name = "F_ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "F_PERMISSION_ID")
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
        private Set<Permission> permissionSet;

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
}
