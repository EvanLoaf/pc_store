package com.gmail.evanloafakahaitao.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "store")
public class ItemsXml {

    private List<ItemXml> xmlItems = new ArrayList<>();

    public List<ItemXml> getXmlItems() {
        return xmlItems;
    }

    @XmlElement(name = "item")
    public void setXmlItems(List<ItemXml> xmlItems) {
        this.xmlItems = xmlItems;
    }
}
