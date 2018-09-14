package com.gmail.evanloafakahaitao.pcstore.service.xml.util;

import com.gmail.evanloafakahaitao.pcstore.service.xml.dto.ItemXMLDTO;
import com.gmail.evanloafakahaitao.pcstore.service.xml.dto.ItemsXMLDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XMLParser {

    private static final Logger logger = LogManager.getLogger(XMLParser.class);

    public List<ItemXMLDTO> parse(File xmlFile) {
        ItemsXMLDTO xmlItems = new ItemsXMLDTO();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ItemsXMLDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            xmlItems = (ItemsXMLDTO) unmarshaller.unmarshal(xmlFile);
            logger.info("XML parse result: ");
            for (ItemXMLDTO xmlItem : xmlItems.getXmlItems()) {
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
