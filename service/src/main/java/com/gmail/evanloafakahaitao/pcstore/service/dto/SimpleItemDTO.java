package com.gmail.evanloafakahaitao.pcstore.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class SimpleItemDTO implements Serializable {

    private static final long serialVersionUID = -937195903295774246L;

    private Long id;
    private String name;
    private String vendorCode;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleItemDTO that = (SimpleItemDTO) o;
        return Objects.equals(vendorCode, that.vendorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorCode);
    }
}
