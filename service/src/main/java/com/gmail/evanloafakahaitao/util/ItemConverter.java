package com.gmail.evanloafakahaitao.util;

import com.gmail.evanloafakahaitao.model.*;
import com.gmail.evanloafakahaitao.model.ItemXml;

import java.util.ArrayList;
import java.util.List;

public class ItemConverter {

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
            System.out.println("Xml items converted to Items");
        } else {
            System.out.println("No xml Items in the List");
        }
        return itemsList;
    }
}
