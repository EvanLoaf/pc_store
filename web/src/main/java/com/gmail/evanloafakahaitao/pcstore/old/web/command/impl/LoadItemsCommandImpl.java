package com.gmail.evanloafakahaitao.pcstore.old.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.XMLService;
import com.gmail.evanloafakahaitao.pcstore.old.web.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoadItemsCommandImpl implements Command {

    @Autowired
    private ItemService itemService;
    @Autowired
    private XMLService xmlService;
    //TODO tyt bil conf manager i daval property..

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
