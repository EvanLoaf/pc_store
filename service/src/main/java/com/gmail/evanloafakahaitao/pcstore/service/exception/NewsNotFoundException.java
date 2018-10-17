package com.gmail.evanloafakahaitao.pcstore.service.exception;

public class NewsNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8680060175775409522L;

    public NewsNotFoundException(String message) {
        super(message);
    }
}
