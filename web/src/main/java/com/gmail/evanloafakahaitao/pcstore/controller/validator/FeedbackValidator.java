package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.service.dto.FeedbackDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FeedbackValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return FeedbackDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors err) {
        FeedbackDTO feedback = (FeedbackDTO) obj;
        ValidationUtils.rejectIfEmpty(err, "message", "feedback.message.empty");

        if (feedback.getMessage() != null && !feedback.getMessage().equals("") && feedback.getMessage().length() > 200) {
            err.rejectValue("message", "feedback.message.length");
        }
    }
}
