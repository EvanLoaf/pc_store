package com.gmail.evanloafakahaitao.dao.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    @NotNull
    @Column
    private String name;
    @NotNull
    @Column
    private Long vendorCode;
    @NotNull
    @Column
    private String description;
    @NotNull
    @Column
    private BigDecimal price;

    public Item() {
    }

    private Item(Builder builder) {
        id = builder.id;
        setName(builder.name);
        setVendorCode(builder.vendorCode);
        setDescription(builder.description);
        setPrice(builder.price);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(Long vendorCode) {
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

    public static final class Builder {
        private Long id;
        private @NotNull String name;
        private @NotNull Long vendorCode;
        private @NotNull String description;
        private @NotNull BigDecimal price;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withName(@NotNull String val) {
            name = val;
            return this;
        }

        public Builder withVendorCode(@NotNull Long val) {
            vendorCode = val;
            return this;
        }

        public Builder withDescription(@NotNull String val) {
            description = val;
            return this;
        }

        public Builder withPrice(@NotNull BigDecimal val) {
            price = val;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
