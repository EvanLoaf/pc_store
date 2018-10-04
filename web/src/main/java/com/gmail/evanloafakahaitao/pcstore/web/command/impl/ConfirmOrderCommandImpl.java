package com.gmail.evanloafakahaitao.pcstore.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.OrderService;
import com.gmail.evanloafakahaitao.pcstore.service.impl.ItemServiceImpl;
import com.gmail.evanloafakahaitao.pcstore.service.impl.OrderServiceImpl;
import com.gmail.evanloafakahaitao.pcstore.web.command.Command;
import com.gmail.evanloafakahaitao.pcstore.web.util.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ConfirmOrderCommandImpl implements Command {

    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;
    private OrderValidator orderValidator = new OrderValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long vendorCode = Long.valueOf(request.getParameter("vendor_code"));
        String itemQuantity = request.getParameter("quantity");
        boolean quantityValidation = orderValidator.validateQuantity(itemQuantity);
        /*if (!quantityValidation) {
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
        }*/
        return null;
    }
}
