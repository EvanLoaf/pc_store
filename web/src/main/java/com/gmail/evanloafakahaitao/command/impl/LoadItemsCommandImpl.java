package com.gmail.evanloafakahaitao.command.impl;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.FileProperties;
import com.gmail.evanloafakahaitao.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.model.Item;
import com.gmail.evanloafakahaitao.ItemService;
import com.gmail.evanloafakahaitao.XmlService;
import com.gmail.evanloafakahaitao.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.impl.XmlServiceImpl;
import com.gmail.evanloafakahaitao.model.ItemXml;
import com.gmail.evanloafakahaitao.util.ItemConverter;
import com.gmail.evanloafakahaitao.command.Command;
import com.gmail.evanloafakahaitao.model.CommandEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoadItemsCommandImpl implements Command {

    private ItemService itemService = new ItemServiceImpl();
    private XmlService xmlService = new XmlServiceImpl();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ItemXml> itemXmlList = xmlService.getXmlItems(
                configurationManager.getProperty(FileProperties.XML_FILE_PATH),
                configurationManager.getProperty(FileProperties.SCHEMA_FILE_PATH)
        );
        List<Item> itemList = ItemConverter.toItems(itemXmlList);
        int savedItems = itemService.save(itemList);
        if (savedItems == 0) {
            request.setAttribute("error", "No new items saved, error occurred");
            return configurationManager.getProperty(PageProperties.USERS_PAGE_PATH);
        } else {
            response.sendRedirect(request.getContextPath() + CommandEnum.ITEMS.getUrl());
        }
        return null;
    }
}
