package com.gmail.evanloafakahaitao.dao.model;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

public class ItemXml {

    private String name;
    private Long vendorcode;
    private String description;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public Long getVendorcode() {
        return vendorcode;
    }

    @XmlElement
    public void setVendorcode(Long vendorcode) {
        this.vendorcode = vendorcode;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
