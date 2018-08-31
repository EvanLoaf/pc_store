package com.gmail.evanloafakahaitao.util;

import com.gmail.evanloafakahaitao.model.ItemXml;
import com.gmail.evanloafakahaitao.model.ItemsXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XmlParser {

    public List<ItemXml> parse(File xmlFile) {
        ItemsXml xmlItems = new ItemsXml();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ItemsXml.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            xmlItems = (ItemsXml) unmarshaller.unmarshal(xmlFile);
            System.out.println("XML parse result: ");
            for (ItemXml xmlItem : xmlItems.getXmlItems()) {
                System.out.printf("Item from xml: name - %s, vendor code - %d, description - %s, price - %.2f\n",
                        xmlItem.getName(), xmlItem.getVendorcode(), xmlItem.getDescription(), xmlItem.getPrice());
            }
        } catch (JAXBException e) {
            System.out.println("Error parsing XML");
            e.printStackTrace();
        }
        return xmlItems.getXmlItems();
    }
}
