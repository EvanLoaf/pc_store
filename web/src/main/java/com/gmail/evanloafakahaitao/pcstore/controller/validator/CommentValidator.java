package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("commentValidator")
public class CommentValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(CommentValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return CommentDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors err) {
        logger.info("Validating comment - create");

        CommentDTO comment = (CommentDTO) obj;
        ValidationUtils.rejectIfEmpty(err, "message", "comment.message.empty");

        if (comment.getMessage() != null && !comment.getMessage().equals("") && comment.getMessage().length() > 180) {
            err.rejectValue("message", "comment.message.length");
        }
    }
}
