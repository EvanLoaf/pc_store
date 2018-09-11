package com.gmail.evanloafakahaitao.web.command.impl;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.service.ItemService;
import com.gmail.evanloafakahaitao.service.XMLService;
import com.gmail.evanloafakahaitao.service.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.service.impl.XMLServiceImpl;
import com.gmail.evanloafakahaitao.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadItemsCommandImpl implements Command {

    private ItemService itemService = new ItemServiceImpl();
    private XMLService xmlService = new XMLServiceImpl();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*List<ItemXMLDTO> itemXmlList = xmlService.getXmlItems(
                configurationManager.getProperty(FileProperties.XML_FILE_PATH),
                configurationManager.getProperty(FileProperties.SCHEMA_FILE_PATH)
        );
        List<Item> itemList = XMLItemConverter.toItems(itemXmlList);
        int savedItems = itemService.save(itemList);
        if (savedItems == 0) {
            request.setAttribute("error", "No new items saved, error occurred");
            return configurationManager.getProperty(PageProperties.USERS_PAGE_PATH);
        } else {
            response.sendRedirect(request.getContextPath() + CommandEnum.ITEMS.getUrl());
        }*/
        return null;
    }
}
