package com.gmail.evanloafakahaitao.pcstore.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class PermissionDTO implements Serializable {

    private Long id;
    private String name;

    public PermissionDTO() {
    }

    private PermissionDTO(Builder builder) {
        id = builder.id;
        setName(builder.name);
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

    public static final class Builder {
        private Long id;
        private String name;

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

        public PermissionDTO build() {
            return new PermissionDTO(this);
        }
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
