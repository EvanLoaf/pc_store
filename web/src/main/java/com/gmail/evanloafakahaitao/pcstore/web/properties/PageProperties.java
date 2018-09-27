package com.gmail.evanloafakahaitao.pcstore.web.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PageProperties {

    private final Environment environment;

    private String loginPagePath;
    private String usersPagePath;
    private String errorsPagePath;
    private String itemsPagePath;
    private String ordersPagePath;
    private String addOrderPagePath;
    private String sendFeedbackPagePath;
    private String showFeedbackPagePath;
    private String registerPagePath;

    @Autowired
    public PageProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    private void initialize() {
        this.loginPagePath = environment.getProperty("login.page.path");
        this.usersPagePath = environment.getProperty("users.page.path");
        this.errorsPagePath = environment.getProperty("errors.page.path");
        this.itemsPagePath = environment.getProperty("items.page.path");
        this.ordersPagePath = environment.getProperty("orders.page.path");
        this.addOrderPagePath = environment.getProperty("add.order.page.path");
        this.sendFeedbackPagePath = environment.getProperty("send.feedback.page.path");
        this.showFeedbackPagePath = environment.getProperty("show.feedback.page.path");
        this.registerPagePath = environment.getProperty("register.page.path");
    }

    public String getLoginPagePath() {
        return loginPagePath;
    }

    public String getUsersPagePath() {
        return usersPagePath;
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public String getOrdersPagePath() {
        return ordersPagePath;
    }

    public String getAddOrderPagePath() {
        return addOrderPagePath;
    }

    public String getSendFeedbackPagePath() {
        return sendFeedbackPagePath;
    }

    public String getShowFeedbackPagePath() {
        return showFeedbackPagePath;
    }

    public String getRegisterPagePath() {
        return registerPagePath;
    }
}
