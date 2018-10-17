package com.gmail.evanloafakahaitao.pcstore.controller.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class APIResponseEntity implements Serializable {

    private static final long serialVersionUID = -2523155896655245041L;

    private String message;
    private Set<String> errors = new HashSet<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }
}
