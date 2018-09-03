package com.gmail.evanloafakahaitao.model;

public class Profile {

    private User user;
    private String address;
    private String telephone;

    private Profile(Builder builder) {
        setUser(builder.user);
        setAddress(builder.address);
        setTelephone(builder.telephone);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public static final class Builder {
        private User user;
        private String address;
        private String telephone;

        private Builder() {
        }

        public Builder withUser(User val) {
            user = val;
            return this;
        }

        public Builder withAddress(String val) {
            address = val;
            return this;
        }

        public Builder withTelephone(String val) {
            telephone = val;
            return this;
        }

        public Profile build() {
            return new Profile(this);
        }
    }
}
