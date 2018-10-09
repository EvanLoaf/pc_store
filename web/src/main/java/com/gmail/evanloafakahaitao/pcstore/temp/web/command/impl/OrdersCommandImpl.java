package com.gmail.evanloafakahaitao.pcstore.temp.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.service.OrderService;
import com.gmail.evanloafakahaitao.pcstore.temp.web.command.Command;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class OrdersCommandImpl implements Command {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PageProperties pageProperties;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*HttpSession session = request.getSession();
        UserPrincipal userPrincipal = (UserPrincipal) session.getAttribute("user");
        Long userId = userPrincipal.getId();
        List<Order> orderList = orderService.findByUserId(userId);
        if (orderList != null) {
            request.setAttribute("orders", orderList);
        } else {
            request.setAttribute("error", "Error retrieving your orders");
        }*/
        return pageProperties.getOrdersPagePath();
    }
}
