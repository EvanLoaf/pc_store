package com.gmail.evanloafakahaitao.util;

public class FeedbackValidator {

    public boolean validate(String feedback) {
        return feedback != null && !feedback.equals("") && feedback.trim().length() <= 200;
    }
}
