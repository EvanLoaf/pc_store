package com.gmail.evanloafakahaitao.pcstore.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class CreateOrderDTO implements Serializable {

    private static final long serialVersionUID = -3895436319279740913L;

    private Integer quantity;
    private String userEmail;
    private String itemVendorCode;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getItemVendorCode() {
        return itemVendorCode;
    }

    public void setItemVendorCode(String itemVendorCode) {
        this.itemVendorCode = itemVendorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrderDTO that = (CreateOrderDTO) o;
        return Objects.equals(quantity, that.quantity) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(itemVendorCode, that.itemVendorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, userEmail, itemVendorCode);
    }
}
