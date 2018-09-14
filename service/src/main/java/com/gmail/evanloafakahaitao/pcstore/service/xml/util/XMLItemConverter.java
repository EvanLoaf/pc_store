package com.gmail.evanloafakahaitao.pcstore.service.xml.util;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Item;
import com.gmail.evanloafakahaitao.pcstore.service.xml.dto.ItemXMLDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class XMLItemConverter {

    private static final Logger logger = LogManager.getLogger(XMLItemConverter.class);

    public static List<Item> toItems(List<ItemXMLDTO> xmlItemsList) {
        List<Item> itemsList = new ArrayList<>();
        if (xmlItemsList != null && !xmlItemsList.isEmpty()) {
            itemsList = new ArrayList<>();
            for (ItemXMLDTO itemXMLDTO : xmlItemsList) {
                Item item = Item.newBuilder()
                        .withName(itemXMLDTO.getName())
                        .withVendorCode(itemXMLDTO.getVendorcode())
                        .withDescription(itemXMLDTO.getDescription())
                        .withPrice(itemXMLDTO.getPrice())
                        .build();
                itemsList.add(item);
            }
            logger.info("Xml items converted to Items");
        } else {
            logger.info("No xml Items in the List");
        }
        return itemsList;
    }
}
