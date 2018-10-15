package com.gmail.evanloafakahaitao.pcstore.controller.model;

import java.math.BigDecimal;

public class ItemDiscountData {

    private Long discountId;
    private BigDecimal minPriceRange;
    private BigDecimal maxPriceRange;

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public BigDecimal getMinPriceRange() {
        return minPriceRange;
    }

    public void setMinPriceRange(BigDecimal minPriceRange) {
        this.minPriceRange = minPriceRange;
    }

    public BigDecimal getMaxPriceRange() {
        return maxPriceRange;
    }

    public void setMaxPriceRange(BigDecimal maxPriceRange) {
        this.maxPriceRange = maxPriceRange;
    }
}
