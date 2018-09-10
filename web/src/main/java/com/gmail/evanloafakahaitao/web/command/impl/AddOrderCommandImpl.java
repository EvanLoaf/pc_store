package com.gmail.evanloafakahaitao.web.command.impl;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.dao.model.Item;
import com.gmail.evanloafakahaitao.service.ItemService;
import com.gmail.evanloafakahaitao.service.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddOrderCommandImpl implements Command {

    private ItemService itemService = new ItemServiceImpl();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long vendorCode = Long.valueOf(request.getParameter("vendor_code"));
        /*Item item = itemService.findByVendorCode(vendorCode);
        if (item == null) {
            request.setAttribute("error", "An error occurred ordering an item");
        } else {
            request.setAttribute("item", item);
        }*/
        return configurationManager.getProperty(PageProperties.ADD_ORDER_PAGE_PATH);
    }
}
