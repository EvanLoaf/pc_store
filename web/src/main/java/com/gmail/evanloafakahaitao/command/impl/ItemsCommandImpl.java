package com.gmail.evanloafakahaitao.command.impl;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.dto.ItemDTO;
import com.gmail.evanloafakahaitao.filter.AuthenticationFilter;
import com.gmail.evanloafakahaitao.model.Item;
import com.gmail.evanloafakahaitao.ItemService;
import com.gmail.evanloafakahaitao.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class ItemsCommandImpl implements Command {

    private ItemService itemService = new ItemServiceImpl();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Item> items = itemService.findAll();
        if (items != null) {
            request.setAttribute("items", items);
        } else {
            request.setAttribute("error", "Error retrieving items");
        }
        return configurationManager.getProperty(PageProperties.ITEMS_PAGE_PATH);
    }
}
