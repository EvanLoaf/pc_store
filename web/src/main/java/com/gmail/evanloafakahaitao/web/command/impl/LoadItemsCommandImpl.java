package com.gmail.evanloafakahaitao.web.command.impl;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.FileProperties;
import com.gmail.evanloafakahaitao.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.dao.model.Item;
import com.gmail.evanloafakahaitao.service.ItemService;
import com.gmail.evanloafakahaitao.service.XmlService;
import com.gmail.evanloafakahaitao.service.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.service.impl.XmlServiceImpl;
import com.gmail.evanloafakahaitao.dao.model.ItemXml;
import com.gmail.evanloafakahaitao.service.util.XmlItemConverter;
import com.gmail.evanloafakahaitao.web.command.Command;
import com.gmail.evanloafakahaitao.web.model.CommandEnum;

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
        List<Item> itemList = XmlItemConverter.toItems(itemXmlList);
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
