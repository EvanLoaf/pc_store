package com.gmail.evanloafakahaitao.pcstore.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.pcstore.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.pcstore.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemsCommandImpl implements Command {

    private ItemService itemService = new ItemServiceImpl();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*List<Item> items = itemService.findAll();
        if (items != null) {
            request.setAttribute("items", items);
        } else {
            request.setAttribute("error", "Error retrieving items");
        }*/
        return configurationManager.getProperty(PageProperties.ITEMS_PAGE_PATH);
    }
}
