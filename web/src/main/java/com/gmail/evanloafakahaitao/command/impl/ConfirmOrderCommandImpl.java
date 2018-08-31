package com.gmail.evanloafakahaitao.command.impl;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.model.Item;
import com.gmail.evanloafakahaitao.ItemService;
import com.gmail.evanloafakahaitao.OrderService;
import com.gmail.evanloafakahaitao.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.impl.OrderServiceImpl;
import com.gmail.evanloafakahaitao.command.Command;
import com.gmail.evanloafakahaitao.model.CommandEnum;
import com.gmail.evanloafakahaitao.model.UserPrincipal;
import com.gmail.evanloafakahaitao.util.OrderValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmOrderCommandImpl implements Command {

    private ItemService itemService = new ItemServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    private OrderValidator orderValidator = new OrderValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long vendorCode = Long.valueOf(request.getParameter("vendor_code"));
        String itemQuantity = request.getParameter("quantity");
        boolean quantityValidation = orderValidator.validateQuantity(itemQuantity);
        if (!quantityValidation) {
            Item item = itemService.findByVendorCode(vendorCode);
            request.setAttribute("item", item);
            request.setAttribute("vendor_code", vendorCode);
            request.setAttribute("error", "Invalid quantity");
            return configurationManager.getProperty(PageProperties.ADD_ORDER_PAGE_PATH);
        }
        HttpSession session = request.getSession();
        UserPrincipal userPrincipal = (UserPrincipal) session.getAttribute("user");
        Long userId = userPrincipal.getId();
        int ordersSaved = orderService.save(userId, vendorCode, Integer.valueOf(itemQuantity));
        if (ordersSaved != 0) {
            response.sendRedirect(request.getContextPath() + CommandEnum.ORDERS.getUrl());
        } else {
            request.setAttribute("error", "Could not save order");
            return configurationManager.getProperty(PageProperties.ORDERS_PAGE_PATH);
        }
        return null;
    }
}
