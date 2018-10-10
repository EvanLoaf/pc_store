package com.gmail.evanloafakahaitao.pcstore.dao.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum PermissionEnum {

    USER_BASIC_PERMISSION,
    SECURITY_ADMIN_BASIC_PERMISSION,
    SALES_ADMIN_BASIC_PERMISSION,
    API_ADMIN_BASIC_PERMISSION,
    VIEW_ITEMS,
    VIEW_ORDERS_SELF,
    CREATE_ORDER,
    DELETE_ORDER_SELF,
    VIEW_USER_SELF,
    UPDATE_USER_SELF,
    CREATE_FEEDBACK,
    VIEW_NEWS,
    CREATE_COMMENT,
    VIEW_USERS_ALL,
    UPDATE_USERS_ALL,
    DISABLE_USER,
    DELETE_USER,
    CREATE_USER,
    VIEW_AUDIT,
    VIEW_ORDERS_ALL,
    UPDATE_ORDER_STATUS,
    CREATE_NEWS,
    UPDATE_NEWS_ALL,
    REMOVE_NEWS_ALL,
    REMOVE_COMMENTS_ALL,
    CREATE_ITEM,
    COPY_ITEM,
    REMOVE_ITEM,
    UPLOAD_ITEMS,
    UPDATE_DISCOUNT_ITEM,
    UPDATE_DISCOUNT_USERS,
    VIEW_FEEDBACK,
    VIEW_ITEMS_API,
    CREATE_ITEM_API,
    UPDATE_ITEM_API,
    DELETE_ITEM_API;

    private static final Logger logger = LogManager.getLogger(PermissionEnum.class);

    public static PermissionEnum getPermission(String permission) {
        try {
            return PermissionEnum.valueOf(permission.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Order Status " + permission.toUpperCase() + " not found", e);
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
