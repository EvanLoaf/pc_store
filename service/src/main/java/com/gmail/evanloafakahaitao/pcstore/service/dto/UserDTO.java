package com.gmail.evanloafakahaitao.pcstore.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserDTO implements Serializable {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private RoleDTO role;
    private ProfileDTO profile;
    private DiscountDTO discount;

    public UserDTO() {
    }

    private UserDTO(Builder builder) {
        setId(builder.id);
        setEmail(builder.email);
        setPassword(builder.password);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setRole(builder.role);
        setProfile(builder.profile);
        setDiscount(builder.discount);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public DiscountDTO getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDTO discount) {
        this.discount = discount;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO user = (UserDTO) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public static final class Builder {
        private Long id;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private RoleDTO role;
        private ProfileDTO profile;
        private DiscountDTO discount;

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

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withFirstName(String val) {
            firstName = val;
            return this;
        }

        public Builder withLastName(String val) {
            lastName = val;
            return this;
        }

        public Builder withRole(RoleDTO val) {
            role = val;
            return this;
        }

        public Builder withProfile(ProfileDTO val) {
            profile = val;
            return this;
        }

        public Builder withDiscount(DiscountDTO val) {
            discount = val;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this);
        }
    }
}
