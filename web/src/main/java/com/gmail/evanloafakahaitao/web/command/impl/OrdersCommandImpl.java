package com.gmail.evanloafakahaitao.web.command.impl;

import com.gmail.evanloafakahaitao.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.dao.model.Order;
import com.gmail.evanloafakahaitao.service.OrderService;
import com.gmail.evanloafakahaitao.service.impl.OrderServiceImpl;
import com.gmail.evanloafakahaitao.web.command.Command;
import com.gmail.evanloafakahaitao.web.model.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrdersCommandImpl implements Command {

    private OrderService orderService = new OrderServiceImpl();
    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

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
        return configurationManager.getProperty(PageProperties.ORDERS_PAGE_PATH);
    }
}
