package com.gmail.evanloafakahaitao.pcstore.temp.web.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum RequestEnum {
    GET,
    POST;

    private static final Logger logger = LogManager.getLogger(RequestEnum.class);

    public static RequestEnum getRequest(String request) {
        try {
            return RequestEnum.valueOf(request.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Command " + request.toUpperCase() + " not found", e);
        }
        return null;
    }
}
