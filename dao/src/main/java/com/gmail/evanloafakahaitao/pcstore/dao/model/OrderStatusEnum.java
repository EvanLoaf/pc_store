package com.gmail.evanloafakahaitao.pcstore.dao.model;

public enum OrderStatusEnum {

    NEW,
    REVIEWING,
    IN_PROGRESS,
    DELIVERED;

    public static OrderStatusEnum getRole(String role) {
        try {
            return OrderStatusEnum.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Order Status " + role.toUpperCase() + " not found");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
