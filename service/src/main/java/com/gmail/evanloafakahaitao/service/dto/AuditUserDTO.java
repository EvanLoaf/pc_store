package com.gmail.evanloafakahaitao.service.dto;

public class AuditUserDTO {

    private String name;
    private String email;
    private RoleDTO role;

    public AuditUserDTO() {
    }

    private AuditUserDTO(Builder builder) {
        setName(builder.name);
        setEmail(builder.email);
        setRole(builder.role);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public static final class Builder {
        private String name;
        private String email;
        private RoleDTO role;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withEmail(String val) {
            email = val;
            return this;
        }

        public Builder withRole(RoleDTO val) {
            role = val;
            return this;
        }

        public AuditUserDTO build() {
            return new AuditUserDTO(this);
        }
    }
}
