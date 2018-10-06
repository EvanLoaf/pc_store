package com.gmail.evanloafakahaitao.pcstore.controller.command.impl;

import com.gmail.evanloafakahaitao.pcstore.service.OrderService;
import com.gmail.evanloafakahaitao.pcstore.controller.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DeleteOrderCommandImpl implements Command {

    @Autowired
    private OrderService orderService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uuid = request.getParameter("uuid");
        /*int deletedOrders = orderService.deleteByUuid(uuid);
        if (deletedOrders != 0) {
            response.sendRedirect(request.getContextPath() + CommandEnum.ORDERS.getUrl());
        } else {
            request.setAttribute("error", "Could not delete order");
            return configurationManager.getProperty(PageProperties.ORDERS_PAGE_PATH);
        }*/
        return null;
    }
}
