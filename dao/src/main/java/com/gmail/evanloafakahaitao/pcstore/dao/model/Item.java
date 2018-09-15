package com.gmail.evanloafakahaitao.pcstore.dao.model;

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
    private String vendorCode;
    @NotNull
    @Column
    private String description;
    @NotNull
    @Column
    private BigDecimal price;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ItemDiscount",
            joinColumns = @JoinColumn(name = "itemId", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "discountId", nullable = false, updatable = false)
    )
    private Set<Discount> discounts = new HashSet<>();

    public Item() {
    }

    private Item(Builder builder) {
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

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(vendorCode, item.vendorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorCode);
    }

    public static final class Builder {
        private Long id;
        private @NotNull String name;
        private @NotNull String vendorCode;
        private @NotNull String description;
        private @NotNull BigDecimal price;
        private Set<Discount> discounts;

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

        public Builder withVendorCode(@NotNull String val) {
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

        public Builder withDiscounts(Set<Discount> val) {
            discounts = val;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
