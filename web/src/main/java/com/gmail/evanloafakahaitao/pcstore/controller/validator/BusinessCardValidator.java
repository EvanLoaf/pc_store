package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.service.dto.BusinessCardDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BusinessCardValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return BusinessCardDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors err) {
        BusinessCardDTO businessCard = (BusinessCardDTO) obj;

        ValidationUtils.rejectIfEmpty(err, "title", "business.card.title.empty");
        ValidationUtils.rejectIfEmpty(err, "fullName", "business.card.fullname.empty");
        ValidationUtils.rejectIfEmpty(err, "workingTelephone", "business.card.working.telephone.empty");

        if (!businessCard.getTitle().equals("") && businessCard.getTitle().length() > 20) {
            err.rejectValue("title", "business.card.title.length");
        }
        if (!businessCard.getFullName().equals("") && businessCard.getFullName().length() > 40) {
            err.rejectValue("fullName", "business.card.fullname.length");
        }
        if (!businessCard.getWorkingTelephone().equals("") && businessCard.getWorkingTelephone().length() > 20) {
            err.rejectValue("workingTelephone", "business.card.working.telephone.length");
        }
    }
}
