package com.gmail.evanloafakahaitao.web.model;

import java.io.Serializable;

public class UserPrincipal implements Serializable {

    private Long id;
    private String email;
    private String name;
    private RoleEnum role;

    private UserPrincipal(Builder builder) {
        id = builder.id;
        email = builder.email;
        name = builder.name;
        role = builder.role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public RoleEnum getRole() {
        return role;
    }

    public static final class Builder {
        private Long id;
        private String email;
        private String name;
        private RoleEnum role;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withEmail(String val) {
            email = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withRole(RoleEnum val) {
            role = val;
            return this;
        }

        public UserPrincipal build() {
            return new UserPrincipal(this);
        }
    }
}
