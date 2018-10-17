package com.gmail.evanloafakahaitao.pcstore.service.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4475601567697181795L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
