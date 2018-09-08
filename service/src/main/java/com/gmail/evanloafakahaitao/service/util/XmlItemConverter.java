package com.gmail.evanloafakahaitao.service.util;

import com.gmail.evanloafakahaitao.dao.model.Item;
import com.gmail.evanloafakahaitao.dao.model.ItemXml;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class XmlItemConverter {

    private static final Logger logger = LogManager.getLogger(XmlItemConverter.class);

    public static List<Item> toItems(List<ItemXml> xmlItemsList) {
        List<Item> itemsList = new ArrayList<>();
        if (xmlItemsList != null && xmlItemsList.size() != 0) {
            itemsList = new ArrayList<>();
            for (ItemXml itemXml : xmlItemsList) {
                Item item = Item.newBuilder()
                        .withName(itemXml.getName())
                        .withVendorCode(itemXml.getVendorcode())
                        .withDescription(itemXml.getDescription())
                        .withPrice(itemXml.getPrice())
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
