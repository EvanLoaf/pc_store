package com.gmail.evanloafakahaitao.pcstore.web.command.impl;

import com.gmail.evanloafakahaitao.pcstore.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.pcstore.config.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.service.OrderService;
import com.gmail.evanloafakahaitao.pcstore.service.impl.OrderServiceImpl;
import com.gmail.evanloafakahaitao.pcstore.web.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class OrdersCommandImpl implements Command {

    @Autowired
    private OrderService orderService;
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
