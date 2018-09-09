package com.gmail.evanloafakahaitao.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class ProfileDTO implements Serializable {

    private Long userId;
    private String address;
    private String phoneNumber;

    public ProfileDTO() {
    }

    private ProfileDTO(Builder builder) {
        setUserId(builder.userId);
        setAddress(builder.address);
        setPhoneNumber(builder.phoneNumber);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static final class Builder {
        private Long userId;
        private String address;
        private String phoneNumber;

        private Builder() {
        }

        public Builder withUserId(Long val) {
            userId = val;
            return this;
        }

        public Builder withAddress(String val) {
            address = val;
            return this;
        }

        public Builder withPhoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public ProfileDTO build() {
            return new ProfileDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileDTO that = (ProfileDTO) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
