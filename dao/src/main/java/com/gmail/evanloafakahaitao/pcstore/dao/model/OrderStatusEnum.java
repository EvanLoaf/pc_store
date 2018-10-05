package com.gmail.evanloafakahaitao.pcstore.dao.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum OrderStatusEnum {

    NEW,
    REVIEWING,
    IN_PROGRESS,
    DELIVERED;

    private static final Logger logger = LogManager.getLogger(OrderStatusEnum.class);

    public static OrderStatusEnum getRole(String role) {
        try {
            return OrderStatusEnum.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Order Status " + role.toUpperCase() + " not found", e);
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
