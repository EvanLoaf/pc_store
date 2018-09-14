package com.gmail.evanloafakahaitao.pcstore.service.xml.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "store")
public class ItemsXMLDTO {

    private List<ItemXMLDTO> xmlItems = new ArrayList<>();

    public List<ItemXMLDTO> getXmlItems() {
        return xmlItems;
    }

    @XmlElement(name = "item")
    public void setXmlItems(List<ItemXMLDTO> xmlItems) {
        this.xmlItems = xmlItems;
    }
}
