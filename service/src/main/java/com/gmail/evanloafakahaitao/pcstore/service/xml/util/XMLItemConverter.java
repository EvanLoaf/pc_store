package com.gmail.evanloafakahaitao.pcstore.service.xml.util;

import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.xml.dto.ItemXMLDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class XMLItemConverter {

    private static final Logger logger = LogManager.getLogger(XMLItemConverter.class);

    public List<ItemDTO> toItems(List<ItemXMLDTO> xmlItemsList) {
        List<ItemDTO> itemsList = new ArrayList<>();
        if (xmlItemsList != null && !xmlItemsList.isEmpty()) {
            itemsList = new ArrayList<>();
            for (ItemXMLDTO itemXMLDTO : xmlItemsList) {
                ItemDTO item = new ItemDTO();
                item.setName(itemXMLDTO.getName());
                item.setVendorCode(itemXMLDTO.getVendorCode());
                item.setDescription(itemXMLDTO.getDescription());
                item.setPrice(itemXMLDTO.getPrice());
                itemsList.add(item);
            }
            logger.info("Xml items converted to Items");
        } else {
            logger.info("No xml Items in the List from File");
        }
        return itemsList;
    }
}
