package com.gmail.evanloafakahaitao.service.dto;

public class OrderUserDTO {

    private String email;
    private String name;
    private ProfileDTO profile;

    public OrderUserDTO() {
    }

    private OrderUserDTO(Builder builder) {
        setEmail(builder.email);
        setName(builder.name);
        setProfile(builder.profile);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    public static final class Builder {
        private String email;
        private String name;
        private ProfileDTO profile;

        private Builder() {
        }

        public Builder withEmail(String val) {
            email = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withProfile(ProfileDTO val) {
            profile = val;
            return this;
        }

        public OrderUserDTO build() {
            return new OrderUserDTO(this);
        }
    }
}
