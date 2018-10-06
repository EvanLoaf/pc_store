package com.gmail.evanloafakahaitao.pcstore.service.xml.dto;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

public class ItemXMLDTO {

    private String name;
    private String vendorCode;
    private String description;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    @XmlElement
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
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
