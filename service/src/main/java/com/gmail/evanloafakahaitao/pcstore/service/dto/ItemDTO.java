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
}
