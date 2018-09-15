package com.gmail.evanloafakahaitao.pcstore.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ItemDTO implements Serializable {

    private Long id;
    private String name;
    private String vendorCode;
    private String description;
    private BigDecimal price;
    private Set<DiscountDTO> discounts = new HashSet<>();

    public ItemDTO() {
    }

    private ItemDTO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setVendorCode(builder.vendorCode);
        setDescription(builder.description);
        setPrice(builder.price);
        setDiscounts(builder.discounts);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<DiscountDTO> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<DiscountDTO> discounts) {
        this.discounts = discounts;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) o;
        return Objects.equals(vendorCode, itemDTO.vendorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorCode);
    }

    public static final class Builder {
        private Long id;
        private String name;
        private String vendorCode;
        private String description;
        private BigDecimal price;
        private Set<DiscountDTO> discounts = new HashSet<>();

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

        public Builder withVendorCode(String val) {
            vendorCode = val;
            return this;
        }

        public Builder withDescription(String val) {
            description = val;
            return this;
        }

        public Builder withPrice(BigDecimal val) {
            price = val;
            return this;
        }

        public Builder withDiscounts(Set<DiscountDTO> val) {
            discounts = val;
            return this;
        }

        public ItemDTO build() {
            return new ItemDTO(this);
        }
    }
}
