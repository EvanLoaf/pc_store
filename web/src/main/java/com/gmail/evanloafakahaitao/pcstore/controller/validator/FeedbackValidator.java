package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.service.dto.FeedbackDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("feedbackValidator")
public class FeedbackValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(FeedbackValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return FeedbackDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors err) {
        logger.info("Validating feedback - create");

        FeedbackDTO feedback = (FeedbackDTO) obj;
        ValidationUtils.rejectIfEmpty(err, "message", "feedback.message.empty");

        if (feedback.getMessage() != null && !feedback.getMessage().equals("") && feedback.getMessage().length() > 200) {
            err.rejectValue("message", "feedback.message.length");
        }
    }
}
