package com.gmail.evanloafakahaitao.pcstore.service.util;

import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class XMLItemSaverUtil {

    private static final Logger logger = LogManager.getLogger(XMLItemSaverUtil.class);

    private final ItemService itemService;

    @Autowired
    public XMLItemSaverUtil(ItemService itemService) {
        this.itemService = itemService;
    }

    public List<String> saveUploadedItems(List<ItemDTO> items) {
        logger.info("Saving Uploaded Items from XML File");
        List<String> vendorCodeDuplicates = new ArrayList<>();
        for (ItemDTO item : items) {
            ItemDTO persistentItem = itemService.findByVendorCode(item.getVendorCode());
            if (persistentItem != null) {
                vendorCodeDuplicates.add(item.getVendorCode());
            } else {
                itemService.save(item);
            }
        }
        return vendorCodeDuplicates;
    }
}
