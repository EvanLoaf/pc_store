package com.gmail.evanloafakahaitao.pcstore.old.web.model;

public enum RoleEnum {
    USER,
    ADMIN;

    public static RoleEnum getRole(String role) {
        try {
            return RoleEnum.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Role " + role.toUpperCase() + " not found");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
