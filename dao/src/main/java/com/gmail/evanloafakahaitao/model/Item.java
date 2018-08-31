package com.gmail.evanloafakahaitao.model;

import java.math.BigDecimal;
import java.util.List;

public class Item {

    private List<Order> ordersForItem;
    private Integer id;
    private String name;
    private Long vendorCode;
    private String description;
    private BigDecimal price;

    private Item(Builder builder) {
        ordersForItem = builder.ordersForItem;
        id = builder.id;
        name = builder.name;
        vendorCode = builder.vendorCode;
        description = builder.description;
        price = builder.price;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<Order> getOrdersForItem() {
        return ordersForItem;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getVendorCode() {
        return vendorCode;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static final class Builder {
        private List<Order> ordersForItem;
        private Integer id;
        private String name;
        private Long vendorCode;
        private String description;
        private BigDecimal price;

        private Builder() {
        }

        public Builder withOrdersForItem(List<Order> val) {
            ordersForItem = val;
            return this;
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withVendorCode(Long val) {
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

        public Item build() {
            return new Item(this);
        }
    }
}
