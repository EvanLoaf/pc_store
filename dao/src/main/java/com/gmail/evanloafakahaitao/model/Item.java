package com.gmail.evanloafakahaitao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    private List<Order> ordersForItem;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "vendor_code")
    private Long vendorCode;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;

    public Item() {
    }

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

    public Long getId() {
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

    public void setOrdersForItem(List<Order> ordersForItem) {
        this.ordersForItem = ordersForItem;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVendorCode(Long vendorCode) {
        this.vendorCode = vendorCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static final class Builder {
        private List<Order> ordersForItem;
        private Long id;
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

        public Builder withId(Long val) {
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
