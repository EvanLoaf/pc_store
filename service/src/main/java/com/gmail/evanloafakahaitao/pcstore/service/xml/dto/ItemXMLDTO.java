package com.gmail.evanloafakahaitao.pcstore.service.xml.dto;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

public class ItemXMLDTO {

    private String name;
    private String vendorcode;
    private String description;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getVendorcode() {
        return vendorcode;
    }

    @XmlElement
    public void setVendorcode(String vendorcode) {
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
