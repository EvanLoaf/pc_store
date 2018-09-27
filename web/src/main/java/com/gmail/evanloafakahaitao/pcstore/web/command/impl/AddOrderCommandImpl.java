package com.gmail.evanloafakahaitao.pcstore.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.pcstore.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.pcstore.web.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AddOrderCommandImpl implements Command {

    @Autowired
    private ItemService itemService;
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
