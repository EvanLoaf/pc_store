package com.gmail.evanloafakahaitao.pcstore.dao.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum PermissionEnum {

    NEW,
    REVIEWING,
    IN_PROGRESS,
    DELIVERED;

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
