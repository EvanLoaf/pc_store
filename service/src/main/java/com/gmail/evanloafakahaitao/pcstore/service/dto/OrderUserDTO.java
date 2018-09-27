package com.gmail.evanloafakahaitao.pcstore.service.dto;

import java.util.Objects;

public class OrderUserDTO {

    private String email;
    private String name;
    private ProfileDTO profile;
    private DiscountDTO discount;

    public OrderUserDTO() {
    }

    private OrderUserDTO(Builder builder) {
        setEmail(builder.email);
        setName(builder.name);
        setProfile(builder.profile);
        setDiscount(builder.discount);
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

    public DiscountDTO getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDTO discount) {
        this.discount = discount;
    }

    public static final class Builder {
        private String email;
        private String name;
        private ProfileDTO profile;
        private DiscountDTO discount;

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

        public Builder withDiscount(DiscountDTO val) {
            discount = val;
            return this;
        }

        public OrderUserDTO build() {
            return new OrderUserDTO(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderUserDTO that = (OrderUserDTO) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
