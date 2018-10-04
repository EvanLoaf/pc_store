package com.gmail.evanloafakahaitao.pcstore.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.pcstore.web.command.Command;
import com.gmail.evanloafakahaitao.pcstore.web.properties.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ItemsCommandImpl implements Command {

    @Autowired
    private ItemService itemService;
    @Autowired
    private PageProperties pageProperties;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*List<Item> items = itemService.findAll();
        if (items != null) {
            request.setAttribute("items", items);
        } else {
            request.setAttribute("error", "Error retrieving items");
        }*/
        return pageProperties.getItemsPagePath();
    }
}
