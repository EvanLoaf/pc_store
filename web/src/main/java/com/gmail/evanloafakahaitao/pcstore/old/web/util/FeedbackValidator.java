package com.gmail.evanloafakahaitao.pcstore.old.web.util;

public class FeedbackValidator {

    public boolean validate(String feedback) {
        return feedback != null && !feedback.equals("") && feedback.trim().length() <= 200;
    }
}
