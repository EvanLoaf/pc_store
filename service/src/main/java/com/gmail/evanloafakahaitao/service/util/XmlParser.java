package com.gmail.evanloafakahaitao.service.util;

import com.gmail.evanloafakahaitao.service.model.ItemXml;
import com.gmail.evanloafakahaitao.service.model.ItemsXml;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XmlParser {

    private static final Logger logger = LogManager.getLogger(XmlParser.class);

    public List<ItemXml> parse(File xmlFile) {
        ItemsXml xmlItems = new ItemsXml();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ItemsXml.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            xmlItems = (ItemsXml) unmarshaller.unmarshal(xmlFile);
            logger.info("XML parse result: ");
            for (ItemXml xmlItem : xmlItems.getXmlItems()) {
                logger.info(
                        String.format(
                        "Item from xml: name - %s, vendor code - %d, description - %s, price - %.2f",
                        xmlItem.getName(), xmlItem.getVendorcode(), xmlItem.getDescription(), xmlItem.getPrice()
                        )
                );
            }
        } catch (JAXBException e) {
            logger.error(e.getMessage(), e);
        }
        return xmlItems.getXmlItems();
    }
}
