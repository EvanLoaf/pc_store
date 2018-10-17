package com.gmail.evanloafakahaitao.pcstore.controller.validator.api;

import com.gmail.evanloafakahaitao.pcstore.service.dto.BusinessCardDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BusinessCardAPIValidator {

    private static final Logger logger = LogManager.getLogger(BusinessCardAPIValidator.class);

    public Set<String> validate(BusinessCardDTO businessCard) {
        logger.info("Validating business card - create API");
        Set<String> errors = new HashSet<>();

        if (businessCard.getTitle() == null || businessCard.getTitle().equals("")) {
            errors.add("Title can't be empty");
        } else if (businessCard.getFullName().length() > 20) {
            errors.add("Title can't be longer than 20 characters");
        }

        if (businessCard.getFullName() == null || businessCard.getFullName().equals("")) {
            errors.add("Full name can't be empty");
        } else if (businessCard.getFullName().length() > 40) {
            errors.add("Full name can't be longer than 40 characters");
        }

        if (businessCard.getWorkingTelephone() == null || businessCard.getWorkingTelephone().equals("")) {
            errors.add("Working telephone can't be empty");
        } else if (businessCard.getFullName().length() > 20) {
            errors.add("Working telephone can't be longer than 20 characters");
        }

        return errors;
    }
}
