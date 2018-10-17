package com.gmail.evanloafakahaitao.pcstore.controller.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PageProperties {

    @Value("${pagination.max.results:5}")
    private Integer paginationMaxResults;
    @Value("${register.page.path}")
    private String registerPagePath;
    @Value("${login.page.path}")
    private String loginPagePath;
    @Value("${errors.page.path}")
    private String errorsPagePath;
    @Value("${items.page.path}")
    private String itemsPagePath;
    @Value("${item.create.page.path}")
    private String itemCreatePagePath;
    @Value("${items.upload.page.path}")
    private String itemsUploadPagePath;
    @Value("${items.set.discount.page.path}")
    private String itemsSetDiscountPagePath;
    @Value("${users.page.path}")
    private String usersPagePath;
    @Value("${user.profile.page.path}")
    private String userProfilePagePath;
    @Value("${user.update.page.path}")
    private String userUpdatePagePath;
    @Value("${users.set.discount.page.path}")
    private String usersSetDiscountPagePath;
    @Value("${orders.page.path}")
    private String ordersPagePath;
    @Value("${order.create.page.path}")
    private String orderCreatePagePath;
    @Value("${news.page.path}")
    private String newsPagePath;
    @Value("${news.create.page.path}")
    private String newsCreatePagePath;
    @Value("${news.show.one.page.path}")
    private String newsShowOnePagePath;
    @Value("${feedback.page.path}")
    private String feedbackPagePath;
    @Value("${feedback.create.page.path}")
    private String feedbackCreatePagePath;
    @Value("${business.cards.page.path}")
    private String businessCardsPagePath;
    @Value("${business.card.create.page.path}")
    private String businessCardCreatePagePath;

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

    public String getItemsPagePath() {
        return itemsPagePath;
    }

    public String getItemCreatePagePath() {
        return itemCreatePagePath;
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

    public String getFeedbackPagePath() {
        return feedbackPagePath;
    }

    public String getFeedbackCreatePagePath() {
        return feedbackCreatePagePath;
    }

    public String getBusinessCardsPagePath() {
        return businessCardsPagePath;
    }

    public String getBusinessCardCreatePagePath() {
        return businessCardCreatePagePath;
    }
}
