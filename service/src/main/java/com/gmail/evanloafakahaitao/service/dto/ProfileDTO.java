package com.gmail.evanloafakahaitao.service.dto;

import java.io.Serializable;

public class ProfileDTO implements Serializable {

    private String address;
    private String phoneNumber;

    public ProfileDTO() {
    }

    private ProfileDTO(Builder builder) {
        setAddress(builder.address);
        setPhoneNumber(builder.phoneNumber);
    }

    public static Builder newBuilder() {
        return new Builder();
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
        private String address;
        private String phoneNumber;

        private Builder() {
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
}
