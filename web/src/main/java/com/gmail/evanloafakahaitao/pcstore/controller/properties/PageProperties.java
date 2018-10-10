package com.gmail.evanloafakahaitao.pcstore.controller.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class PageProperties {

    private final Environment environment;

    private String schemaFilePath;
    private Integer paginationMaxResults;
    private String registerPagePath;
    private String loginPagePath;
    private String errorsPagePath;
    private String errorsAccessPagePath;
    private String itemsPagePath;
    private String itemCreatePagePath;
    private String itemUpdatePagePath;
    private String itemsUploadPagePath;
    private String itemsSetDiscountPagePath;
    private String usersPagePath;
    private String userCreatePagePath;
    private String userProfilePagePath;
    private String userUpdatePagePath;
    private String usersSetDiscountPagePath;
    private String ordersPagePath;
    private String orderCreatePagePath;
    private String newsPagePath;
    private String newsCreatePagePath;
    private String newsShowOnePagePath;
    private String newsUpdatePagePath;
    private String feedbackPagePath;
    private String feedbackCreatePagePath;
    private String auditPagePath;

    @Autowired
    public PageProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    private void initialize() {
        this.schemaFilePath = environment.getProperty("schema.file.path");
        this.paginationMaxResults = Integer.valueOf(Objects.requireNonNull(environment.getProperty("pagination.max.results")));
        this.registerPagePath = environment.getProperty("register.page.path");
        this.loginPagePath = environment.getProperty("login.page.path");
        this.errorsPagePath = environment.getProperty("errors.page.path");
        this.errorsAccessPagePath = environment.getProperty("errors.access.page.path");
        this.itemsPagePath = environment.getProperty("items.page.path");
        this.itemCreatePagePath = environment.getProperty("item.create.page.path");
        this.itemUpdatePagePath = environment.getProperty("item.update.page.path");
        this.itemsUploadPagePath = environment.getProperty("items.upload.page.path");
        this.itemsSetDiscountPagePath = environment.getProperty("items.set.discount.page.path");
        this.usersPagePath = environment.getProperty("users.page.path");
        this.userCreatePagePath = environment.getProperty("user.create.page.path");
        this.userProfilePagePath = environment.getProperty("user.profile.page.path");
        this.userUpdatePagePath = environment.getProperty("user.update.page.path");
        this.usersSetDiscountPagePath = environment.getProperty("users.set.discount.page.path");
        this.ordersPagePath = environment.getProperty("orders.page.path");
        this.orderCreatePagePath = environment.getProperty("order.create.page.path");
        this.newsPagePath = environment.getProperty("news.page.path");
        this.newsCreatePagePath = environment.getProperty("news.create.page.path");
        this.newsShowOnePagePath = environment.getProperty("news.show.one.page.path");
        this.newsUpdatePagePath = environment.getProperty("news.update.page.path");
        this.feedbackPagePath = environment.getProperty("feedback.page.path");
        this.feedbackCreatePagePath = environment.getProperty("feedback.create.page.path");
        this.auditPagePath = environment.getProperty("audit.page.path");
    }

    public String getSchemaFilePath() {
        return schemaFilePath;
    }

    public Integer getPaginationMaxResults() {
        return paginationMaxResults;
    }

    public String getRegisterPagePath() {
        return registerPagePath;
    }

    public String getLoginPagePath() {
        return loginPagePath;
    }

    public String getErrorsPagePath() {
        return errorsPagePath;
    }

    public String getErrorsAccessPagePath() {
        return errorsAccessPagePath;
    }

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public String getItemCreatePagePath() {
        return itemCreatePagePath;
    }

    public String getItemUpdatePagePath() {
        return itemUpdatePagePath;
    }

    public String getItemsUploadPagePath() {
        return itemsUploadPagePath;
    }

    public String getItemsSetDiscountPagePath() {
        return itemsSetDiscountPagePath;
    }

    public String getUsersPagePath() {
        return usersPagePath;
    }

    public String getUserCreatePagePath() {
        return userCreatePagePath;
    }

    public String getUserProfilePagePath() {
        return userProfilePagePath;
    }

    public String getUserUpdatePagePath() {
        return userUpdatePagePath;
    }

    public String getUsersSetDiscountPagePath() {
        return usersSetDiscountPagePath;
    }

    public String getOrdersPagePath() {
        return ordersPagePath;
    }

    public String getOrderCreatePagePath() {
        return orderCreatePagePath;
    }

    public String getNewsPagePath() {
        return newsPagePath;
    }

    public String getNewsCreatePagePath() {
        return newsCreatePagePath;
    }

    public String getNewsShowOnePagePath() {
        return newsShowOnePagePath;
    }

    public String getNewsUpdatePagePath() {
        return newsUpdatePagePath;
    }

    public String getFeedbackPagePath() {
        return feedbackPagePath;
    }

    public String getFeedbackCreatePagePath() {
        return feedbackCreatePagePath;
    }

    public String getAuditPagePath() {
        return auditPagePath;
    }
}
