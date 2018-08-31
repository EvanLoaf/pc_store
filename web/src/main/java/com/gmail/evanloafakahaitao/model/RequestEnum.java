package com.gmail.evanloafakahaitao.model;

public enum RequestEnum {
    GET,
    POST;

    public static RequestEnum getRequest(String request) {
        try {
            return RequestEnum.valueOf(request.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Command " + request.toUpperCase() + " not found");
            e.printStackTrace();
        }
        return null;
    }
}
